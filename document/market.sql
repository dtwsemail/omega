--��Ʒ��Ϣ��

CREATE TABLE `market_goods_info` (
  `goods_id` varchar(32) NOT NULL COMMENT '��Ʒ���',
  `goods_name` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '�û����',
  `login_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '��¼��',
  `login_pwd` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '��¼����',
  `nick` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '�ǳ�',
  `status` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '״̬',
  `retry_times` int(11) DEFAULT NULL COMMENT '���Դ���',
  `create_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `update_time` datetime DEFAULT NULL COMMENT '�޸�ʱ��',
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
