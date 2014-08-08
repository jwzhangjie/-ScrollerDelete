-ScrollerDelete
===============

ListView滑动，点击删除，文章见http://blog.csdn.net/jwzhangjie/article/details/39006007
在Scroller的应用--滑屏实现中使用Scroller实现滑屏效果，这里使用Scroller与ListView实现类似QQ滑动，
然后点击删除功能，设计思路是Item使用Scroller实现滑动，ListView根据触摸判断是横向滑动还是竖直滑动，
关于点击事件处理思路：对于View的onClick事件跟平常一样，里面针对OnItemClick做了处理，
判断触摸距离来判断，如果小于5的话，在Item的onTouchEvent方法中的MotionEvent.ACTION_UP里面返回false，
这样ListView里面的dispatchTouchEvent的super.dispatchTouchEvent(event)就会返回false,
根据x,y获取当前position以及点击的view，调用super.performItemClick(view, position, view.getId());
来告诉ListView出发onItemClick事件。
