package com.xf.base.http.api;

/**
 * 常见错误码对应的错误信息
 * Created by dmx on 16/12/1.
 */

public class ErrorMessage {


    /**
     * 根据编码获取对应的错误信息，没有对应编码则返回null
     *
     * @param code
     * @return
     */
    public static String get(int code) {
        switch (code) {
//            case 1:
//                return "系统错误";
//
//            case 4:
//                return "非法访问";
//
//            case 5:
//                return "用户凭证过期";
//
//            case 20:
//                return "用户不存在";
//
//            case 21:
//                return "用户已经存在";
//
//            case 22:
//                return "用户状态已改变,请刷新重试";
//
//            case 100:
//                return "验证码获取太频繁";
//
//            case 101:
//                return "获取验证码次数限制";
//
//            case 102:
//                return "验证码错误";
//            case 103:
//                return "密码错误";
//            case 104:
//                return "第一次登录需要设置密码";
//            case 500:
//                return "接口不存在";
//
//            case 502:
//                return "已经提交代理申请";
//
//            case 504:
//                return "已经是雇员了";
////            case 528:
////                return "不在续保期内,无法续保";
//            case 661:
//                return "客户身份证重复";
//
//            case 1000:
//                return "employeeId错误或者缺少";
//
//
//            case 1009:
//                return "手机号错误";
//
//            case 1020:
//                return "参数pwd错误或者缺失";
//
//            case 1022:
//                return "参数id错误或者缺失";
            default:
                return null;

        }
    }
}
