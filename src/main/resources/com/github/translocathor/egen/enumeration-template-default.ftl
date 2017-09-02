package ${packageName};

public enum ${enumName} {
<#list enumKeys as key>
    ${key}<#if key?is_last>;<#else>,</#if>
</#list>
}