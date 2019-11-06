/**
 * es检索项目
 * 1，读入文档；
 * 	文件夹时遍历
 * 	创建文档对象
 *  txt无法读取内容；TXTParser
 *  批量读写；FileInfo存储文件信息；线程池跑，CycleBarrier封装IndexQuery；bulk批量
 * 2，插入es；spring data或者bulk
 * 	配置es，注入Transport实例
 * 
 * 3，检索
 * 4，前端，ftl
 * 
 */
package com.bonc;