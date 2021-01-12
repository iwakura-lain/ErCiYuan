package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.service.manager.listener.ExcelAnimeListener;
import cn.antigenmhc.otaku.service.manager.pojo.Subject;
import cn.antigenmhc.otaku.service.manager.mapper.SubjectMapper;
import cn.antigenmhc.otaku.service.manager.pojo.excel.ExcelAnime;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeSubjectVo;
import cn.antigenmhc.otaku.service.manager.service.SubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 动漫类别 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void batchImportByExcel(InputStream excelStream) {
        EasyExcel.read(excelStream, ExcelAnime.class,
                new ExcelAnimeListener(this))
                .sheet()
                .doRead();
    }

    @Override
    public Subject getByTitle(String title) {
        QueryWrapper<Subject> query = new QueryWrapper<>();
        query.eq("title", title);
        query.eq("parent_id", "0");
        return baseMapper.selectOne(query);
    }

    @Override
    public boolean hasEqualsTwiceSubject(Subject subject) {
        QueryWrapper<Subject> query = new QueryWrapper<>();
        query.eq("title", subject.getTitle());
        query.eq("parent_id", subject.getParentId());
        return baseMapper.selectOne(query) != null;
    }

    @Override
    public List<AnimeSubjectVo> nestedAnimeSubjectList() {
        //查询所有数据
        List<Subject> allSubject = baseMapper.selectList(null);
        //根据 parent_id 进行排序，避免因为子类别在前导致无法形成映射关系
        allSubject.sort(Comparator.comparing(Subject::getParentId));
        //映射关系表，子类别数组列表
        Map<AnimeSubjectVo, List<AnimeSubjectVo>> resMap = new ConcurrentHashMap<>();
        List<AnimeSubjectVo> children = new ArrayList<>();
        //组装映射关系, 注意重写 AnimeSubjectVo 的 hashcode 和 equals 方法
        for (Subject subject : allSubject) {
            AnimeSubjectVo subjectVo = new AnimeSubjectVo();
            subjectVo.setId(subject.getId());
            subjectVo.setTitle(subject.getTitle());
            subjectVo.setSort(subject.getSort());

            if("0".equals(subject.getParentId())){
                resMap.put(subjectVo, new ArrayList<>(children));
                children.clear();
            }else{
                //重写的 hashcode 和 equals 方法只对 id 进行判断
                //因此这里只需要封装 id 即可
                AnimeSubjectVo parentVo = new AnimeSubjectVo();
                parentVo.setId(subject.getParentId());
                resMap.get(parentVo).add(subjectVo);
            }
        }

        //组装列表
        for (Map.Entry<AnimeSubjectVo, List<AnimeSubjectVo>> entry : resMap.entrySet()) {
            entry.getKey().setChildrenSubject(entry.getValue());
        }

        return new ArrayList<>(resMap.keySet());
    }
}
