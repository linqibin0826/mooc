package com.linqibin.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linqibin.commonutils.Result;
import com.linqibin.eduservice.entity.EduTeacher;
import com.linqibin.eduservice.entity.dto.TeacherCondition;
import com.linqibin.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Hugh&you
 * @since 2020-10-13
 */
@RestController  //@ResponseBody @Controller @Target
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    private EduTeacherService teacherService;

    @Autowired
    public void setTeacherService(EduTeacherService teacherService) {
        this.teacherService = teacherService;
    }


    /**
     * 查询所有讲师
     *
     * @param
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14
     */
    @ApiOperation("查询所有讲师")
    @GetMapping("/findAll")
    public Result findAllTeacher() {
        List<EduTeacher> teacherList = teacherService.list(null);
        return Result.ok().data("items", teacherList);
    }

    /**
     * 根据讲师ID逻辑删除讲师
     *
     * @param id
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14 19:59
     */
    @ApiOperation("根据讲师ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@PathVariable String id) {
        boolean removeById = teacherService.removeById(id);
        if (removeById) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    /**
     * 分页查询教师列表
     *
     * @param currentPage
     * @param limit
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14
     */
    @ApiOperation("根据分页信息查找讲师")
    @GetMapping("pageTeacher/{currentPage}/{limit}")
    public Result pageListTeacher(@PathVariable Long currentPage,
                                  @PathVariable Long limit) {

        //mp中的分页对象
        Page<EduTeacher> teacherPage = new Page<>(currentPage, limit);

        //底层会把结果自动封装回teacherPage中,
        teacherService.page(teacherPage, null);

        //封装对象,返回JSON到前端
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", teacherPage.getTotal());
        hashMap.put("rows", teacherPage.getRecords());
        return Result.ok().data(hashMap);
    }


    /**
     * 多条件查询带分页
     *
     * @param currentPage
     * @param limit
     * @param teacherCondition
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14
     */
    @ApiOperation("多条件查询教师信息(条件可为空)")
    @PostMapping("pageTeacherCondition/{currentPage}/{limit}")
    public Result pageTeacherCondition(
            @ApiParam(name = "currentPage", value = "当前页码", required = true)
            @PathVariable Long currentPage,
            @ApiParam(name = "limit", value = "每页记录数量", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherCondition", value = "查询条件")
            @RequestBody(required = false) TeacherCondition teacherCondition) {

        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(currentPage, limit);

        //条件对象
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //多条件组合查询拼接(动态sql)
        String name = teacherCondition.getName();
        Integer level = teacherCondition.getLevel();
        String begin = teacherCondition.getBegin();
        String end = teacherCondition.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.gt("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.lt("gmt_modified", end);
        }

        //按降序排序
        wrapper.orderByDesc("gmt_create");

        //查询
        teacherService.page(teacherPage, wrapper);

        //结果封装
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", teacherPage.getTotal());
        hashMap.put("rows", teacherPage.getRecords());
        return Result.ok().data(hashMap);
    }


    /**
     * 添加教师
     *
     * @param eduTeacher
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14
     */
    @ApiOperation("添加教师")
    @PostMapping("addTeacher")
    public Result addTeacher(@ApiParam(name = "教师对象", value = "教师对象实体, 不需要传id、gmt_create、gmt_modified三个字段", required = true)
                             @RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        return save ? Result.ok() : Result.error();
    }

    /**
     * 根据指定ID查询教师
     *
     * @param id
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14
     */
    @ApiOperation("根据指定ID查询教师")
    @GetMapping("getTeacherById/{id}")
    public Result getTeacherById(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return Result.ok().data("teacher", eduTeacher);
    }

    /**
     * 指定ID修改教师
     *
     * @param eduTeacher
     * @return Result
     * @author linqibin&youmei
     * @creed: ProjectForSDDM
     * @date 2020/10/14 19:57
     */
    @ApiOperation("根据指定Id修改教师信息")
    @PostMapping("updateTeacherById")
    public Result updateTeacherById(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        return flag ? Result.ok() : Result.error();
    }

    @GetMapping("/getHotTeachers")
    public Result getHotTeachers() {
        List<EduTeacher> teachers = teacherService.getHotTeacher();
        return Result.ok().data("items", teachers);
    }

    @GetMapping("/getTeacherAndCoursesById/{teacherId}")
    public Result getTeacherAndCoursesById(@PathVariable String teacherId) {
        HashMap<String, Object> map = teacherService.getTeacherAndCoursesById(teacherId);
        return Result.ok().data(map);
    }
}

