package icu.junyao.back.service.impl;

import cn.hutool.core.util.StrUtil;
import icu.junyao.back.entity.CourseSubsection;
import icu.junyao.back.mapper.CourseSubsectionMapper;
import icu.junyao.back.req.CourseSubsectionEditReq;
import icu.junyao.back.req.CourseSubsectionReq;
import icu.junyao.back.res.CourseSubsectionRes;
import icu.junyao.back.service.CourseSubsectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.back.vod.VodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author johnson
 * @since 2021-10-23
 */
@Service
@RequiredArgsConstructor
public class CourseSubsectionServiceImpl extends ServiceImpl<CourseSubsectionMapper, CourseSubsection> implements CourseSubsectionService {

    private final VodService vodService;

    @Override
    public CourseSubsectionRes gainCourseSubsection(String id) {
        CourseSubsection courseSubsection = super.getById(id);
        CourseSubsectionRes courseSubsectionRes = new CourseSubsectionRes();
        BeanUtils.copyProperties(courseSubsection, courseSubsectionRes);
        return courseSubsectionRes;
    }

    @Override
    public void saveCourseSubsection(CourseSubsectionReq courseSubsectionReq) {
        CourseSubsection courseSubsection = new CourseSubsection();
        BeanUtils.copyProperties(courseSubsectionReq, courseSubsection);
        super.save(courseSubsection);
    }

    @Override
    public void updateCourseSubsection(CourseSubsectionEditReq courseSubsectionEditReq) {
        CourseSubsection courseSubsection = new CourseSubsection();
        BeanUtils.copyProperties(courseSubsectionEditReq, courseSubsection);
        super.updateById(courseSubsection);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeCourseSubsection(String id) {
        // 先删除小节视频
        String videoSourceId = super.getById(id).getVideoSourceId();
        if (StrUtil.isNotEmpty(videoSourceId)) {
            vodService.removeAlyVideo(videoSourceId);
        }

        super.removeById(id);
    }
}
