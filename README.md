 mvn archetype:generate -DgroupId=org.myshop -DartifactId=myshop -DarchetypeArtifactId=maven-archetype-webapp
 spring stopwatch  时间监控  http://www.cnblogs.com/woshimrf/p/5677337.html

 总结
      ① 如果涉及到堆栈，队列等操作，应该考虑用List。如果要进行大量的随机访问，应使用ArrayList；如果经常进行插入与删除操作，用使用LinkedList。
      ② HashMap设计用来快速访问；而TreeMap保持“键”始终处于排序状态，所以没有HashMap快。LinkedHashMap保持元素插入的顺序，但是也通过散列提供了快速访问能力。
      ③ Set不接受重复元素。HashSet提供最快的查询速度，而TreeSet保持元素处于排序状态。LinkedHashSet以插入顺序保存元素。
      ④ 对哈希表的操作，作为key的对象要正确重写equals和hashCode方法。
      ⑤ 尽量返回接口而非实际的类型（针对抽象编程），如返回List而非ArrayList，这样如果以后需要将ArrayList换成LinkedList时，客户端代码不用改变。
      ⑥ 程序中不应该使用过时的Vector\Hashtable\Stack。
