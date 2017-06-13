--数据库初始化脚本
--创建数据库
CREATE DATABASE myshop;
-- 使用数据库
use myshop;
--创建用户数据库表

CREATE TABLE IF NOT EXISTS `user` (
`uid` bigint NOT NULL AUTO_INCREMENT COMMENT '用户UID',
`name` VARCHAR(255) NOT NULL COMMENT '用户名',
`username`  VARCHAR(255) NOT NULL COMMENT '用户账号',
`password` VARCHAR(255) NOT NULL COMMENT '密码',
`email` VARCHAR(255) NOT NULL COMMENT '邮箱',
`phone` bigint NOT NULL  COMMENT '电话',
`description` VARCHAR(255) NOT NULL COMMENT '个人简介',
`createtime` TIMESTAMP NOT NULL COMMENT '创建时间',
`updatetime` TIMESTAMP NOT NULL COMMENT '修改时间',
`birthday` TIMESTAMP NOT NULL COMMENT '出生日期',
`region` SMALLINT(6) DEFAULT '0' COMMENT '地区',
`status`INT(11) DEFAULT '0' COMMENT '状态',
`type`  VARCHAR(255) NOT NULL COMMENT '用户类型',
`version` bigint   COMMENT '版本',
  PRIMARY KEY (uid),
  key idx_updatetime(updatetime),
  key idx_uid(uid),
  key idx_createtime(createtime)
)ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8 COMMENT='用户表';



--创建秒杀库存表

CREATE TABLE seckill(
  `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP NOT NULL COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT  CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

--初始化数据
INSERT INTO seckill(name, number, start_time, end_time) values
  ('1000元秒杀iPhone6', 100, '2015-11-01 00:00:00', '2015-11-02 00:00:00'),
  ('500元秒杀iPad', 200, '2015-11-01 00:00:00', '2015-11-02 00:00:00'),
  ('300元秒杀小米', 300, '2015-11-01 00:00:00', '2015-11-02 00:00:00'),
  ('200元秒杀红米Note', 400, '2015-11-01 00:00:00', '2015-11-02 00:00:00');

  --秒杀成功明细表
  --用户登录认证相关信息
  CREATE TABLE success_killed (
    `seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
    `user_phone` bigint NOT NULL COMMENT '用户手机号',
    `state` tinyint NOT NULl DEFAULT -1 COMMENT '状态标示：-1： 无效 0：成功 1：已付款',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    PRIMARY KEY(seckill_id, user_phone), /*联合主键*/
    key idx_create_time(create_time)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

  --连接数据库的控制台
  mysql -uroot -p

  ALTER TABLE seckill
  DROP INDEX idx_create_time;
  ADD index idx_c_s(start_time, create_time)
  --上线 V 1.2


  --资源位card模板 表

CREATE TABLE `myshop`.`operation_resource_cardtemplate` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `card_id` BIGINT(20) NULL COMMENT '卡片模板id',
  `template_name` VARCHAR(100) NOT NULL COMMENT '模板名称',
  `card_type` TINYINT(4) NULL,
  `card_img_url` VARCHAR(100) NULL DEFAULT NULL COMMENT 'card图片url',
  `json_code` TEXT NULL DEFAULT NULL COMMENT 'json代码',
  `json_code_draft` TEXT NULL DEFAULT NULL COMMENT 'json代码-draft',
  `platform` TINYINT(4) NOT NULL COMMENT '推送平台：0，无 1，移动端 2，PC端 3，Pad模板 4，TV模板',
  `status` TINYINT(4) NOT NULL COMMENT '数据状态：-1：删除 0：停用 1：启用',
  `create_user` BIGINT(20) NOT NULL COMMENT '创建人',
  `create_time` DATETIME NULL COMMENT '创建时间',
  `update_user` BIGINT(20) NULL COMMENT '修改人',
  `update_time` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
  `publish_state` INT(2) NULL DEFAULT '0' COMMENT '发布状态 0未发布 1已发布 2更改未发布',
  PRIMARY KEY (`id`),
  INDEX `index_cardid` (`card_id` ASC),
  INDEX `index_cardid_platform` (`card_id` ASC, `platform` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 348
DEFAULT CHARACTER SET = utf8
COMMENT = '卡片模板';
