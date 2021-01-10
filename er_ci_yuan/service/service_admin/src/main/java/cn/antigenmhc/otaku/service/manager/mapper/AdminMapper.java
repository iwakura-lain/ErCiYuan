package cn.antigenmhc.otaku.service.manager.mapper;

import cn.antigenmhc.otaku.service.manager.pojo.Admin;
import cn.antigenmhc.otaku.service.manager.pojo.vo.AdminQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 管理员 Mapper 接口
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Repository
public interface AdminMapper extends BaseMapper<Admin> {

    IPage<Admin> selectAdminByQuery(Page<?> page, AdminQueryVo queryVo);

    List<Admin> getRecordsNameByKey(String key);

    List<String> getNameList();
}
