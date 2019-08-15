<!DOCTYPE html>
<html lang="en">
<head>
    
</head>

<body>
    hello，${product.name}
    <hr/>
    id:${product.id}   <br/>
    name:${product.name}        <br/>
    salePoint:${product.salePoint}       <br/>
    price:${product.price}              <br/>
    createTime:${product.creatTime?date}    <br/>
    createTime:${product.creatTime?time}     <br/>
    createTime:${product.creatTime?datetime}   <br/>
    <hr/>
    遍历集合
    <#list list as p>
        ID:${p.id}             <br/>
        name:${p.name}               <br/>
        time:${p.creatTime?time}           <br/>
    <hr/>
    </#list>
    <hr/>
    <h1>逻辑判断</h1>
    <#list list as p>
        <#if (p.id>5)>
            id大鱼5的产品
        <#elseif (p.id<5) >
        id在1-5区间
        <#else>
        id在10以上
        </#if>
         <hr/>
    </#list>
    <hr/>
    <!--空值处理-->
    <#list list as p>
        <#if (p.id<5)>
        ${p.image!}
        ${p.image!'该值为null'}
            <#if p.image??>
            该值不为null
            <#else >
            该值为null
           </#if>
    </#if>
    </#list>



</body>
</html>
