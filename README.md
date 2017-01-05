#mybatis-generator-plugin-extend
```xml
<!-- 给实体添加序列UID 而不实现接口-->
<plugin type="com.puyixiaowo.mybatis.generator.plugin.AddSerialVersionUIDPlugin"></plugin>

<!-- 删除主键,当实体配置rootClass继承了baseEntity可使用此插件删除主键 -->
<plugin type="com.puyixiaowo.mybatis.generator.plugin.RemoveColumnPlugin">
	<property name="fieldName" value="id"/>
</plugin>

<!-- mapper xml namespace 不生成mapper.java 单独生成xml修改mapper的namespace-->
<plugin type="com.puyixiaowo.mybatis.generator.plugin.NamespacePlugin">
	<property name="packageName" value="com.puyixiaowo.tnews.dao.impl"/>
</plugin>
```
NamespacePlugin需修改源码