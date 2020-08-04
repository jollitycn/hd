package com.insigma.ordercenter.base;

/**
 * @program: hd
 * @description: 错误CODE和MSG
 * @author: XuChao
 * @create: 2019-12-24 14:57
 **/
public class CodeMsg {

    private int retCode;
    private String message;

    // 按照模块定义CodeMsg
     public static CodeMsg API_FAILED = new CodeMsg(10004, "平台接口调用失败");
    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "操作成功");
    public static CodeMsg SERVER_EXCEPTION = new CodeMsg(500100, "服务端异常");
    public static CodeMsg PARAMETER_ISNULL = new CodeMsg(500101, "输入参数为空");
    public static CodeMsg PARAMETER_ERROR = new CodeMsg(500102, "输入参数异常");
    public static CodeMsg PERMISSION_ERROR = new CodeMsg(500001, "您的账号已被停用，请重新申请");
    public static CodeMsg SYS_USER_NOT_LOGIN = new CodeMsg(500000, "后台账号未登录");

    // 业务异常
    public static CodeMsg USER_NOT_EXIST = new CodeMsg(500103, "用户不存在");
    public static CodeMsg ONLINE_USER_OVER = new CodeMsg(500104, "在线用户数超出允许登录的最大用户限制。");
    public static CodeMsg SESSION_NOT_EXIST = new CodeMsg(500105, "不存在离线session数据");
    public static CodeMsg NOT_FIND_DATA = new CodeMsg(500106, "查找不到对应数据");
    public static CodeMsg USER_PASS_ERROR = new CodeMsg(500107, "密码错误!");
    public static CodeMsg DATA_INSERT_ERROR = new CodeMsg(500108, "数据添加失败");
    public static CodeMsg DATA_DELETE_ERROR = new CodeMsg(500109, "数据删除失败");
    public static CodeMsg DATA_UPDATE_ERROR = new CodeMsg(500110, "数据更新失败");
    public static CodeMsg DIC_CODE_REPEAT = new CodeMsg(500111, "数据字典编码不能重复");
    public static CodeMsg DIC_DELETE_ERROR = new CodeMsg(500112, "该数据字典有子数据，无法删除");
    public static CodeMsg USER_HAS_STOPPED = new CodeMsg(500115, "用户已禁用");
    public static CodeMsg IMAGE_VALIDATE_CODE_ERROR = new CodeMsg(500116, "图形验证码错误");
    public static CodeMsg USER_ACCOUNT_REPEAT = new CodeMsg(500117, "用户帐号不能重复");
    public static CodeMsg MOBILE_NOT_MATCH = new CodeMsg(500122, "手机号码输入错误");
    public static CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500129, "该手机号未注册");
    public static CodeMsg MSG_WRONG = new CodeMsg(500130, "短信验证码错误");
    public static CodeMsg ROLE_UPDATE_FAIL=new CodeMsg(500131,"角色更新失败");
    public static CodeMsg ROLE_NAME_REPEAT=new CodeMsg(500132,"角色名称不能重复");
    public static CodeMsg MOBILE_USED = new CodeMsg(500133, "该手机号已注册");
    public static CodeMsg LACK_OF_PARAM = new CodeMsg(500134, "缺少参数");
    public static CodeMsg LACK_OF_WHID = new CodeMsg(500135,"仓库id不能为空");
    public static CodeMsg PRODUCT_NOT_EXIST = new CodeMsg(500136,"商品不存在");
    public static CodeMsg STOKE_NOT_EXIST = new CodeMsg(500137,"库存记录不存在");
    public static CodeMsg STRATEGY_NOT_EXIST = new CodeMsg(500138,"策略不存在");
    public static CodeMsg SHOP_NOT_EXIST = new CodeMsg(500139,"店铺不存在");
    public static CodeMsg STRATEGY_PARAM_NOT_EXIST = new CodeMsg(500140,"策略参数不存在");
    public static CodeMsg ROLE_USER = new CodeMsg(500141,"角色删除失败，该角色下绑定账号，不允许删除");
    public CodeMsg(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public int getRetCode() {
        return retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
