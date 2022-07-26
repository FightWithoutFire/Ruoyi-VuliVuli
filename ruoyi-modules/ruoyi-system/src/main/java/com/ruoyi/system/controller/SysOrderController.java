package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

/**
 * 订单 Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/order")
public class SysOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    @GetMapping("/add/{userId}")
    public AjaxResult add(@PathVariable("userId") Long userId) throws IOException {
        SysOrder sysOrder = new SysOrder();
        sysOrder.setUserId(userId);
        sysOrder.setStatus("0");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(Objects.requireNonNull(mediaType))
                .build();
        Request request = new Request.Builder()
                .url("http://119.91.212.182:8081/api/snowflake/get/test")
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
        sysOrder.setOrderNo(Objects.requireNonNull(response.body()).string());
        return AjaxResult.success(sysOrderService.insertSysOrder(sysOrder));
    }

    @GetMapping("/list")
    public AjaxResult list(SysOrder sysOrder)
    {
        return AjaxResult.success(sysOrderService.selectSysOrderList(sysOrder));
    }

    @GetMapping("/query/{orderId}")
    public AjaxResult query(@PathVariable("orderId") Long orderId)
    {
        return AjaxResult.success(sysOrderService.selectSysOrderById(orderId));
    }
}
