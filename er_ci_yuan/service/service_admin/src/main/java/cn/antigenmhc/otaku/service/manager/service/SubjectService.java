package cn.antigenmhc.otaku.service.manager.service;

import cn.antigenmhc.otaku.service.manager.pojo.Subject;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AnimeSubjectVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 动漫类别 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 通过 Excel 文件批量上传
     * @param excelStream：文件流
     */
    void batchImportByExcel(InputStream excelStream);

    /**
     * 根据名字查找数据
     * @param title：标题名
     * @return：subject 对象
     */
    Subject getByTitle(String title);

    /**
     * 判断是否存在相同类别
     * @param subject：组装了的二级类别对象
     * @return：数据库中是否已经存在该二级类别
     */
    boolean hasEqualsTwiceSubject(Subject subject);

    /**
     * 返回所有的数据，以嵌套列表形式返回，用于前端树形展示
     * @return：封装了的自关联对象
     */
    List<AnimeSubjectVo> nestedAnimeSubjectList();
}
