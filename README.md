## DDL
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
***
## 默认静态资源访问
* http://localhost:8081/webjars/jquery/3.4.1/jquery.js
* http://localhost:8081/jquery-1.11.3.js
* http://localhost:8081
* 注意浏览器标签页图标
***
## Thymeleaf
http://localhost:8081/index
***
## druid监控
http://localhost:8081/druid  admin/admin
***
## 增删改查，加入缓存redis(不清楚连接池用没用)，log在项目中的使用
* http://localhost:8081/addUser?name=lisi&age=18
* http://localhost:8081/deleteUser/4
* http://localhost:8081/updateUser?id=1&name=zhaoliu&age=33
* http://localhost:8081/user/1
* http://localhost:8081/user
***
## 定时任务
在控制台打印
***
## 邮件任务
需要修改配置文件中的mail.password,附件文件的路径
* http://localhost:8081/simpleMail
* http://localhost:8081/complexMail
***
## Swagger2
http://localhost:8081/swagger-ui.html