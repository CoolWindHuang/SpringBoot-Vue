CREATE TABLE `sys_user` (

                            `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                            `password` varchar(50) DEFAULT NULL COMMENT '密码',
                            `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
                            `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
                            `phone` varchar(20) DEFAULT NULL COMMENT '电话',
                            `address` varchar(50) DEFAULT NULL COMMENT '地址',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
                            `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;