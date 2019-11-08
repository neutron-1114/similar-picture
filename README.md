# similar_picture
玩玩图片相似度判断，
都是最简单的demo，实现了基本的功能，还需要改进<br/>
主要为了实现阮一峰所介绍的三种方法
http://www.ruanyifeng.com/blog/2011/07/principle_of_similar_image_search.html
http://www.ruanyifeng.com/blog/2013/03/similar_image_search_part_ii.html

<h2>1、哈希法</h2><br/>
（1）将两个图片的大小统一<br/>
（2）各自将他们进行灰度化<br/>
（3）计算两个图片各自的平均灰度<br/>
（4）图片内部大于平均灰度的置1，否则置0<br/>
（5）对两个byte[]数组进行diff，差异小的为相似图片<br/>

<h2>2、直方图</h2><br/>
（1）将像素的rgb进行分组<br/>
（2）将分组变为一个向量<br/>
（3）求向量的相似度<br/>


<h2>3、基于大律法生成直方图</h2><br/>
（1）图片进行灰度化<br/>
（2）寻找到将图片黑白化最佳的灰度阈值<br/>
（3）将图片黑白化，生成0-1数组<br/>
（4）比较两个01数组，差异小的为相似图片
