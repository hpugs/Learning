package com.hpugs.learning.web.collections;

import com.hpugs.learning.common.constant.Result;
import com.hpugs.learning.web.vo.req.TestReq;
import com.hpugs.learning.web.vo.resp.TestResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/12/1 下午4:40
 */
@RestController
@RequestMapping("/test")
@Api("测试服务")
public class TestCollection {

    @GetMapping("/getUser")
    @ApiOperation(value = "/getUser", notes = "获取用户")
    @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "getUser", dataTypeClass = Long.class, required = true, example = "-1")
    public Result<TestResp> getUser(@RequestParam(required = false, defaultValue = "-1") Long userId){
        return Result.buildSuccess();
    }

    @PostMapping("/queryUser")
    @ApiOperation(value = "/queryUser", notes = "批量查询用户")
    public Result<List<TestResp>> queryUser(@RequestBody TestReq testReq){
        return Result.buildSuccess();
    }

}
