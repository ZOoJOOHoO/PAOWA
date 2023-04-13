# PAOWA
使用redis技术
1 生成token 设置有效期 每次请求都要验证token
2 对数据使用缓存 从redis中取
3 使用list记录前50名 缓存存活时间为5min 缓存失效再去sql中查