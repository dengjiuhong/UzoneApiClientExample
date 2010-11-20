/**
 * UC乐园Rest接口 Java SDK - 例子
 *
 * @category   main
 * @package    com.ucweb.uzoneapiclient
 * @author     Jiuhong Deng <dengjiuhong@gmail.com>
 * @version    $Id:$
 * @copyright  Jiuhong Deng
 * @link       http://u.uc.cn/
 * @since      File available since Release 1.0.0
 */
package com.ucweb.uzoneapiclientexamples;

import com.ucweb.uzoneapiclient.UzoneApiClient;
import java.util.HashMap;
import java.util.Map;
public class App 
{
    public static void main( String[] args )
    {
        // UzoneToken为从乐园进入的时候，乐园传给应用的授权信息
        String UzoneToken           = "XXnxvOlc+vxsqSYHq9PyXxqpdHzFlnvw85yWXaywP6hzoMzespfNYzILLRdw/ZtUtJyvbBOpdNcnd40a+Xv8uw==";
        // 开始读取配置
        Map<String, String> configs = new HashMap<String, String>();
        // 接入乐园的时候分配得到的AppKey
        configs.put("AppKey", "sanguo");
        // 接入乐园的时候分配到的密钥
        configs.put("Secret", "123456");
        // 接入乐园的时候分配到的RSA私钥的路径
        configs.put("PrivateKeyPath", "/tmp/private.key");
        // 接口服务器的入口地址
        configs.put("RestServer", "http://api.u.uc.cn/restserver.php");
        // 单点登录服务的地址
        configs.put("SsoServer", "http://u.uc.cn/index.php?r=sso/auth");
        
        // 实例化一个 ApiClinet 的实例
        UzoneApiClient client = new UzoneApiClient(configs);
        // 设置当前用户的授权标记
        client.setUzoneToken(UzoneToken);
        
        // 如果是非授权的uzone_token
        if (!client.checkIsAuth()){
            // 走单点登录流程, 当前地址
            String BackUrl    = "http://localhost/test.php";
            String RdirectURl = client.getRedirectUrl(BackUrl);
            // 302 跳转上面的url
            //TODO 应用自己实现302跳转
        } else {
            // 手工请求指定的接口例子
            // 1, 获取用户的基本信息
            Map<String, String>  param = new HashMap<String, String>();
            param.put("uids", "10001,10002");
            client.callMathod(param, "user.getInfo");
            // 2, 获取当前用户的好友列表
            Map<String, String>  friendsGetParam = new HashMap<String, String>();
            client.callMathod(friendsGetParam, "friends.get");
        }
        System.out.println( "Good Luck Man~~" );
    }
}
