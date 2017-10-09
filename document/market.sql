--商品信息表

CREATE TABLE `market_goods_info` (
  `goods_id` varchar(32) NOT NULL COMMENT '商品编号',
  `goods_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户编号',
  `login_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录密码',
  `nick` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '昵称',
  `status` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `retry_times` int(11) DEFAULT NULL COMMENT '尝试次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
