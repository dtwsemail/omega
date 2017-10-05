
-- 用户基本信息表
CREATE TABLE `user_base_info` (
  `user_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '用户编号',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `sex` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '性别',
  `mobile` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机',
  `cert_no` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `mail` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `status` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户登录表
CREATE TABLE `user_login_info` (
  `login_id` varchar(32) NOT NULL COMMENT '登录编号',
  `user_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户编号',
  `login_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录密码',
  `nick` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '昵称',
  `status` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `retry_times` int(11) DEFAULT NULL COMMENT '尝试次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--收货地址表
CREATE TABLE `user_delivery_address` (
  `address_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '收货地址编号',
  `province_code` char(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '省',
  `city_code` char(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '市',
  `district_code` char(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '区',
  `detail` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '详细地址',
  `status` varchar(8) DEFAULT NULL COMMENT '状态：0，失效，1，生效',
  `is_default` char(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '是否是默认地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




