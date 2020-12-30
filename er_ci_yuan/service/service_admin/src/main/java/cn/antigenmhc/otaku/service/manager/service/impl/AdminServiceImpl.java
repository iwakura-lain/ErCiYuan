package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.mapper.AdminMapper;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AdminQueryVo;
import cn.antigenmhc.otaku.service.manager.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 管理员 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public IPage<Admin> selectPageByQuery(Page<Admin> adminPage, AdminQueryVo adminQueryVo) {
//        //结果排序方式：根据 sort 字段排序
//        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByAsc("sort");
//
//        //如果没有查询条件，只返回按照sort排序后的结果
//        if(adminQueryVo == null){
//            return baseMapper.selectPage(adminPage, queryWrapper);
//        }
//
//        //根据条件进行查询
//        String name = adminQueryVo.getName();
//        Integer level = adminQueryVo.getLevel();
//        String joinDateBegin = adminQueryVo.getJoinDateBegin();
//        String joinDateEnd = adminQueryVo.getJoinDateEnd();
//        if(!StringUtils.isEmptyOrWhitespaceOnly(name)){
//            //模糊查询不以左百分号开头，避免索引失效
//            queryWrapper.likeRight("name", name);
//        }
//        if(level != null){
//            queryWrapper.eq("level", level);
//        }
//        //joinDateBegin ge(<=) join_date le(<=) joinDateEnd
//        if(!StringUtils.isEmptyOrWhitespaceOnly(joinDateBegin)){
//            queryWrapper.ge("join_date", joinDateBegin);
//        }
//        if(!StringUtils.isEmptyOrWhitespaceOnly(joinDateEnd)){
//            queryWrapper.le("join_date", joinDateEnd);
//        }
        return adminMapper.selectAdminByQuery(adminPage, adminQueryVo);
        //return baseMapper.selectPage(adminPage, queryWrapper);
    }
}
