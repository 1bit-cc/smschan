# SMS Chan
一个短消息推送应用，类似于`Server酱`。

暂时只支持企业微信应用，日后添加其他通道。

## 代办
- [x] 用企业微信应用API写一个临时用。
- [ ] 添加企业微信机器人支持。
- [ ] 添加公众号支持。
- [ ] 添加钉钉支持。

## 使用教程 - v0.0.1

### **配置**
> 根目录新建`config`文件夹，在内部新建如下3个文件：

#### accessTokenFile.txt
- 企业微信API的Token缓存，新建一个空文件即可。

#### tokenFile.txt
- 用来放本API的访问Token，输入自己的Token，建议设置复杂点。
- 例如：`bBEbIh32VRqcCLKaDsYEli`

#### wxData.txt
- 用来放企业微信应用API的使用凭证。
- 格式为：`[corpid],[corpsecret],[企业微信应用ID]`。

### **运行**
#### 运行命令
```
java -jar smschan.jar -Dfile.encoding=UTF-8
```
#### 注意事项
- Java版本：8
- Linux可以不加`-Dfile.encoding=UTF-8`，Windows因为默认编码的问题要加。
### **使用**
#### 发送纯文字
⚠️ **发送带`\n`等符号的信息需要先进行URL编码**
```
格式：
http://[IP]:8080/qwt?token=[API的访问Token]&msg=[待发送信息]

示例：
http://[IP]:8080/qwt?token=bBEbIh32VRqcCLKaDsYEli&msg=你好世界

```

## 版本历史
### v0.0.1
- 第一版，临时用。
- 仅支持给企业微信应用发送信息。
- 刚学没两天，代码挺烂的，日后慢慢改。