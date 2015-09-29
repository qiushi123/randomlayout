#运行效果
![qcl](http://a3.qpic.cn/psb?/V13yyfT93I2jgl/qiDRn5SqcmDfOMIWmW4vnRl5MA8FW*vURZlwZi.84CU!/b/dBwBAAAAAAAA&bo=bwGGAgAAAAACF9k!&rf=viewer_4)



#使用步骤
	1，这个开源项目的使用只需要导入jar包就行。
		这里可以实现文字的随机摆布，并且这些随机的文件可以显示不同的颜色，
		不同的大小。文字进来时有个飞入效果。退出时有个飞出效果

	2，使用的时候可以使用StellarMap这个类来创建显示随机文字的控件
		stellarMap = new StellarMap(this);
		// 1.设置内部的TextView距离四周的内边距
		int padding = 15;
		stellarMap.setInnerPadding(padding, padding, padding, padding);
		stellarMap.setAdapter(new StellarMapAdapter());
		// 设置默认显示第几组的数据
		stellarMap.setGroup(0, true);// 这里默认显示第0组
		// 设置x和y方向上的显示的密度
		stellarMap.setRegularity(15, 15);// 如果值设置的过大，有可能造成子View摆放比较稀疏

 