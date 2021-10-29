package com.github.houbb.sisyphus.test.support.recover;

import com.github.houbb.sisyphus.api.model.RetryAttempt;
import com.github.houbb.sisyphus.api.support.recover.Recover;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
public class MyRecover implements Recover {

    @Override
    public <R> void recover(RetryAttempt<R> retryAttempt) {
        Object[] params = retryAttempt.params();

        String email = params[0].toString();
        // 通知
        System.out.println("[Recover] " + email + "你的邮箱查询失败了！");
    }

}
