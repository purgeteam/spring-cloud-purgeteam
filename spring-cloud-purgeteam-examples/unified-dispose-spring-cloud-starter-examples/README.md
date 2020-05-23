# 全局处理异常拦截例子

请支持我们的项目，给项目加星。感谢啦😉
具体框架请访问：[首页](https://github.com/purgeteam/spirng-cloud-purgeteam)

## 例子项目结构

```
unified-dispose-a-example      消费者服务
unified-dispose-api-example    api包 feign接口
unified-dispose-b-example      生产者服务
```

## 启动

启动项目 A  访问  http://127.0.0.1:11000/test 测试

返回
```json
{
  "succ": false,
  "ts": 1590206466129,
  "data": null,
  "code": "RPC-510",
  "msg": "呀，网络出问题啦！"
}
```

启动项目 B 再次访问项目 A http://127.0.0.1:11000/test 测试

```json
{
  "data": "test",
  "succ": true,
  "ts": 1590206628589
}
```
