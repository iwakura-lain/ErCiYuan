package cn.antigenmhc.otaku.service.manager.listener;

import cn.antigenmhc.otaku.service.manager.pojo.Subject;
import cn.antigenmhc.otaku.service.manager.pojo.excel.ExcelAnime;
import cn.antigenmhc.otaku.service.manager.service.SubjectService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/11 11:38
 * @Version: 1.0
 **/
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ExcelAnimeListener extends AnalysisEventListener<ExcelAnime> {

    private SubjectService subjectService;

    @Override
    public void invoke(ExcelAnime excelAnime, AnalysisContext analysisContext) {
        //获取 Excel 内容
        String firstLevelTitle = excelAnime.getFirstLevelTitle();
        String twiceLevelTitle = excelAnime.getTwiceLevelTitle();
        //获得一级类别
        Subject firstSubject = subjectService.getByTitle(firstLevelTitle);

        //组装一级类别并存入
        if (firstSubject == null){
            firstSubject = new Subject();
            firstSubject.setParentId("0");
            firstSubject.setTitle(firstLevelTitle);
            subjectService.save(firstSubject);
        }

        //组装二级类别
        Subject twiceSubject = new Subject();
        twiceSubject.setTitle(twiceLevelTitle);
        twiceSubject.setParentId(firstSubject.getId());
        //判断二级类别是否已经存在，不存在才存入
        if(!subjectService.hasEqualsTwiceSubject(twiceSubject)){
            subjectService.save(twiceSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("读取完成");
    }
}
