# mybatis-generator-plugin-extend

```xml
<!-- 给实体添加序列UID 而不实现Serializable接口-->
<plugin type="com.puyixiaowo.mybatis.generator.plugin.AddSerialVersionUIDPlugin"></plugin>

<!-- 删除主键,当实体配置rootClass继承了baseEntity可使用此插件删除主键 -->
<plugin type="com.puyixiaowo.mybatis.generator.plugin.RemoveColumnPlugin">
	<property name="fieldName" value="id"/>
</plugin>

<!-- mapper xml namespace 不生成mapper.java 单独生成xml修改mapper的namespace-->
<plugin type="com.puyixiaowo.mybatis.generator.plugin.NamespacePlugin">
	<property name="packageName" value="com.puyixiaowo.tnews.dao.impl"/><!--命名空间包，必须-->
	<property name="classNameAppend" value="DaoImpl" /><!--mapper后缀，非必须，默认"Mapper",如：<mapper namespace="com.puyixiaowo.tnews.dao.impl.TestNewsDaoImpl">-->
	<property name="minusPrefix" value="4" /><!--mapper前缀减去多少个字母，非必须，如原实体名为：TestNews,删减后为:<mapper namespace="com.puyixiaowo.tnews.dao.impl.NewsDaoImpl">-->
</plugin>

<!-- 数字类型，默认int(1以上0)转为Long,tinyint(1)转为Integer-->
<javaTypeResolver type="com.puyixiaowo.mybatis.generator.type.NumberTypeResolver">
</javaTypeResolver>
```
