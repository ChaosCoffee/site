# site
Web site navigation 网站导航

## 背景
网站导航，随时可以访问的收藏夹，为了随时随地可以访问导航主页，而且不用写繁琐重复的页面代码。

## 环境
- 模板引擎渲染页面 [https://www.thymeleaf.org/](https://www.thymeleaf.org/)

模板文件`example.html`  

- Json文件配置(这里就是实际要写的内容`example.json`)
配置如下:
``` json
 {
      "id": "学习资源", //栏目标题(若要增加一栏，则类似加上同样的配置，id不能重复)
      "siteList": [
        {
          "address": "https://www.baidu.com/",  //网站地址
          "name": "百度搜索",  //网站名
          "description": "百度一下，你就知道", //网站描述
          "img": "https://www.baidu.com/favicon.ico" //网站logo(一般都是网站域名+favicon.ico，也可以配置git上仓库地址，真没有的话可以为空)
  }
```

## 发布执行
```
 - mvn compile
 - mvn exec:java -Dexec.mainClass="org.chaoscoffee.HTMLTemplateUtils"
```
`example.html` 放在resources 下面，执行`maven`命令后会自动解析生成一个`result.html`文件,结果都已经放进去了
展示页面就是`result.html`

## 扩展
本地完全可以通过以上步骤，本地跑一遍，生成`result.html`，然后放到主页环境里。  
重复的步骤完全可以通过CI工具来完成，这里使用**免费的**`https://travis-ci.org`，配置完`.travis.yml`脚本就可以自动帮我们部署发布提交等任务。
以后增加网站，只要在`example.json`里加入，就可以通过导航网站主页进行访问。
