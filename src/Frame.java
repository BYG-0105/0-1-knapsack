import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame
{
	JFrame File = new JFrame(); //选择数据文件界面
	int beibaosize;
	int types;
	int[] w = new int [1000];
	int[] v = new int [1000];
	double[][] c = new double [1000][2];
	int wvsum;
	int fileId = -1;
	int dataname = 0;

	/* 文本域，分别来存放物品的重量，价值*/
    JTextField choice; //用户选择
    
	
	JButton Gbutton;//按钮，查看0-1背包问题结果
	JTextArea Twv;//文本区，用来输出物品重量,物品价值
	JTextArea Tresult;//文本区，用来输出物品价值
    ArrayList<Goods> list = new ArrayList<Goods>();;//存放从文件读取的物品对象
    
  public Frame() 
   { 
	  
	
	  
	  


	 
	   JLabel item1 = new JLabel("<html><body>1.选择数据文件 <br> 2.贪心算法<br> 3.回溯算法<br> 4.动态规划算法<br>5.绘制散点图<br> 6.按重量比进行非递增排序<br> 7.以文件形式保存结果</body></html>",JLabel.CENTER);
	   JLabel item2 = new JLabel("                          ",JLabel.CENTER);
	   JLabel item3 = new JLabel("输入你的选择",JLabel.CENTER);

	   /*
	   JLabel huisu = new JLabel("",JLabel.CENTER);
	   JLabel dtgh = new JLabel("",JLabel.CENTER);
	   JLabel paint = new JLabel("",JLabel.CENTER);
	   JLabel wv = new JLabel("",JLabel.CENTER);
	   JLabel txt = new JLabel("",JLabel.CENTER);

*/


       //设置字体及背景设置
	   item1.setFont(new Font("宋体",Font.BOLD,18));
	   item1.setForeground(Color.BLUE);
	   item2.setFont(new Font("宋体",Font.BOLD,20));
	   item2.setForeground(Color.BLUE);
	   item3.setFont(new Font("宋体",Font.BOLD,18));
	   item3.setForeground(Color.BLUE);
	   

	   /*
	   filenames.setPreferredSize(new Dimension(100, 200));
	   
	   tanxin.setFont(new Font("宋体",Font.BOLD,15));
	   tanxin.setForeground(Color.BLUE);
	   
	   huisu.setFont(new Font("宋体",Font.BOLD,15));
	   huisu.setForeground(Color.BLUE);
	   
	   dtgh.setFont(new Font("宋体",Font.BOLD,15));
	   dtgh.setForeground(Color.BLUE);
	   
	   paint.setFont(new Font("宋体",Font.BOLD,15));
	   paint.setForeground(Color.BLUE);
	   
	   wv.setFont(new Font("宋体",Font.BOLD,15));
	   wv.setForeground(Color.BLUE);
*/
	   Twv = new JTextArea(20,30);//给定列数和行数
	   Tresult = new JTextArea(20,30);//给定列数和行数
	   Twv.setEditable(false);
	   Tresult.setEditable(false);
		add(Twv,BorderLayout.SOUTH);//边框布局管理器
		add(Tresult,BorderLayout.SOUTH);//边框布局管理器

		//在文本框上添加滚动条
		JScrollPane jspwv = new JScrollPane(Twv);
		//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
		//jspw.setBounds(13, 10, 350, 340);
		//默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
		//jspw.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JScrollPane jspr = new JScrollPane(Tresult);
		//jspv.setSize(100,100);
		//jspv.setViewportView(Tvalue);
		//设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
		//jspv.setBounds(13, 10, 350, 340);
		//默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
		//jspv.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);JScrollPane.ScrollPaneConstraints.VERTICAL_SCROLLBAR_ALWAYS
		//jspv.setVerticalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	   choice = new JTextField(15);//给定列数的空的JTextField对象
	   choice.setFont(new Font("宋体",Font.BOLD,20));//设置文本域中的字体
      
	 
	   JPanel area=new JPanel();//增加面板
	    area.setBackground(Color.CYAN);//设置面板的背景颜色
		//area.setLayout(new GridLayout(6,2));//网格布局，给定行数和列数
	    //area.setLayout(new BorderLayout());
		//在面板上增加组件
		area.add(item1);
		area.add(item2);


		area.add(jspwv);
		area.add(jspr);
		area.add(item3);

		area.add(choice);		
		add(area,BorderLayout.CENTER);//边框布局管理器
			
		 //增加按钮
		  Gbutton=new JButton("确定");
		   Gbutton.setBackground(Color.pink);//设置按钮的颜色
			//动作监听器
			Gbutton.addActionListener(new ActionListener()
		       {      
		           public void actionPerformed(ActionEvent event)
		           {    
		        	  
		        	  int select = Integer.parseInt(choice.getText());//从文本域中获取输入的值
		        	  if(select == 1)
		        	  {
		        		 Text(); 
		        	  }
		        	  else if(select == 2)
		        	  {
		        		  long start=System.nanoTime();
		        		  
		        		  Algorithm a = new Algorithm();
		        		  double[][] x = new double[1000][1000];
		        		  for(int i = 0;i < types;i++)
		        		  {
		        			  x[i][1] = i;
		        		  }

		        		  x = a.Greedy(x,w,v,types,beibaosize);
		        		  long end=System.nanoTime();
		        		  long Time=end-start;
		        		  
		        		  Tresult.setText(null);
		        		  Tresult.append("最大价值为："+x[types][0]+"\n");
		        		  Tresult.append("物品（重量）：{ ");
		        		  for(int i = 0;i < types-1;i++)
		        		  {
		        			  Tresult.append(w[(int)x[i][1]]+", ");
		        		  }
		        		  Tresult.append(w[(int)x[types-1][1]]+" }"+"\n");
		        		  Tresult.append("解向量为：{ ");
		        		  for(int i = 0;i<types-1;i++)
		        		  {
		        			  Tresult.append(x[i][0]+", ");
		        		  }
		        		  Tresult.append(x[types-1][0]+" }"+"\n");
		        		  Tresult.append("运行时间为："+(double)Time/1000000000+" s\n");
		        		  
		        	  }
		        	  else if(select == 3)
		        	  {
		        		  long start=System.nanoTime();

		        		  Algorithm a = new Algorithm();
		        		  double[] x = new double[1000];
		        		  x = a.Backtracking(w,v,types,beibaosize);
		        		  long end=System.nanoTime();
		        		  long Time=end-start;
		        		  Tresult.setText(null);
		        		  Tresult.append("最大价值为："+x[types]+"\n");
		        		  Tresult.append("物品（重量）为：{ ");
		        		  for(int i = 0;i<types-1;i++)
		        		  {
		        			  Tresult.append(w[i]+", ");
		        		  }
		        		  Tresult.append(w[types-1]+" }"+"\n");
		        		  Tresult.append("解向量为：{ ");
		        		  for(int i = 0;i<types-1;i++)
		        		  {
		        			  Tresult.append((int)x[i]+", ");
		        		  }
		        		  Tresult.append((int)x[types-1]+" }"+"\n");
		        		  Tresult.append("运行时间为："+(double)Time/1000000000+" s\n");

		        	  }
		        	  else if(select == 4)
		        	  {
		        		  long start=System.nanoTime();

		        		  Algorithm a = new Algorithm();
		        		  int[] x = new int[100];
		        		  x = a.Dynamic(w,v,types,beibaosize);
		        		  long end=System.nanoTime();
		        		  long Time=end-start;
		        		  Tresult.setText(null);
		        		  Tresult.append("最大价值为："+x[types]+"\n");
		        		  Tresult.append("物品（重量）为：{ ");
		        		  for(int i = 0;i<types-1;i++)
		        		  {
		        			  Tresult.append(w[i]+", ");
		        		  }
		        		  Tresult.append(w[types-1]+" }"+"\n");
		        		  Tresult.append("解向量为：{ ");
		        		  for(int i = 0;i<types-1;i++)
		        		  {
		        			  Tresult.append(x[i]+", ");
		        		  }
		        		  Tresult.append(x[types-1]+" }"+"\n");
		        		  Tresult.append("运行时间为："+(double)Time/1000000000+" s\n");

		        	  }
		        	  else if(select == 5)
		        	  {
		        		 
		        		  JFrame f = new JFrame();
//		        		  JButton button = new JButton("关闭");;//按钮，查看0-1背包问题结果
//		        		  button.addActionListener(new ActionListener()
//		   		         {      
//		   		           public void actionPerformed(ActionEvent event)
//		   		           {    
//		   		        	  f.dispose();
//		   		           }
//		   		        });
//		        		  JPANEL A = NEW JPANEL();
//		        		  A.ADD(BUTTON);
		        		  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        		 // f.add(a);
		        		  f.add(new Paint(w,v,types));
		        		  f.setSize(400,300);
		        		  f.setLocation(240,200);
		        		  f.setVisible(true);
		        		 
		        	  }
		        	  else if(select == 6)
		        	  {
		        		  Tresult.setText(null);
		        		  for(int i = 0;i < types;i++)
		        		  {
		        			  c[i][0] =(double)v[i]/(double)w[i];
		        			  c[i][1] = i+1;
		        			 // System.out.println(c[i][1] );
		        			  //System.out.println(c[i][0] );

		        		  }
		        		  Algorithm a = new Algorithm();
		        		  c = a.Selectsort(c, 0, types);
		        		  for(int i = 0;i < types;i++)
		        		  {
		        			  Tresult.append("第"+(int)c[i][1]+"个物品的重量比为："+c[i][0]+"\n");
		        		  }
		        	  }
		        	  else if(select == 7)
		        	  {
		        		  try {

		        			    File writeName = new File("D:\\2021-2022\\大三下\\软件工程经济\\Git\\0-1\\0-1-knapsack\\result\\data"+dataname+".txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
		        			    dataname++;
		        			    if(!writeName.exists()) {
		        			        writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
		        			    }
		        			    FileWriter writer = new FileWriter(writeName);
		        			    BufferedWriter out = new BufferedWriter(writer);
		        			    String data = Tresult.getText();
		        			    out.write(data);
		        			    out.flush(); // 把缓存区内容压入文件    out.close();
		        			} catch (IOException e) {
		        			    e.printStackTrace();
		        			}
		        		  JOptionPane.showMessageDialog(null,"结果保存成功","提示消息",JOptionPane.INFORMATION_MESSAGE);  
		        	  }
		        	  else
		        	  {
		        		  JOptionPane.showMessageDialog(null,"所输入选择有误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
		                  //清空输入框  
		                  choice.setText("");
		        	  }
		           }
		       });
			area.add(Gbutton);//在面板上添加按钮
		
   }

	  public void Text()
	   {
			
		   JButton Gbutton;//按钮，查看数据文件信息
		   
		   JLabel item1 = new JLabel("0-1背包问题",JLabel.CENTER);
		   JLabel item2 = new JLabel("选择所要读取数据的文件（共有10个文件） ",JLabel.CENTER);
	        //设置字体及背景设置
		   item1.setFont(new Font("宋体",Font.BOLD,23));
		   item1.setForeground(Color.BLUE);
		   item2.setFont(new Font("宋体",Font.BOLD,22));
		   item2.setForeground(Color.BLUE);
		   
		   JRadioButton jrb1=new JRadioButton("beibao0");  
		   JRadioButton jrb2=new JRadioButton("beibao1"); 
		   JRadioButton jrb3=new JRadioButton("beibao2");  
		   JRadioButton jrb4=new JRadioButton("beibao3");
		   JRadioButton jrb5=new JRadioButton("beibao4");  
		   JRadioButton jrb6=new JRadioButton("beibao5");
		   JRadioButton jrb7=new JRadioButton("beibao6");  
		   JRadioButton jrb8=new JRadioButton("beibao7");
		   JRadioButton jrb9=new JRadioButton("beibao8");  
		   JRadioButton jrb10=new JRadioButton("beibao9");
		   
		   jrb1.setFont(new Font("宋体",Font.BOLD,22));
		   jrb2.setFont(new Font("宋体",Font.BOLD,22));
		   jrb3.setFont(new Font("宋体",Font.BOLD,22));
		   jrb4.setFont(new Font("宋体",Font.BOLD,22));
		   jrb5.setFont(new Font("宋体",Font.BOLD,22));
		   jrb6.setFont(new Font("宋体",Font.BOLD,22));
		   jrb7.setFont(new Font("宋体",Font.BOLD,22));
		   jrb8.setFont(new Font("宋体",Font.BOLD,22));
		   jrb9.setFont(new Font("宋体",Font.BOLD,22));
		   jrb10.setFont(new Font("宋体",Font.BOLD,22));


		   
		   ButtonGroup bg=new ButtonGroup(); 
		   
		   bg.add(jrb1);
		   bg.add(jrb2);
		   bg.add(jrb3);
		   bg.add(jrb4);
		   bg.add(jrb5);
		   bg.add(jrb6);
		   bg.add(jrb7);
		   bg.add(jrb8);
		   bg.add(jrb9);
		   bg.add(jrb10);
		   jrb1.setSelected(true);  
		   
	       JPanel jrbarea=new JPanel();//增加面板
	       jrbarea.add(jrb1);
	       jrbarea.add(jrb2);
	       jrbarea.add(jrb3);
	       jrbarea.add(jrb4);
	       jrbarea.add(jrb5);
	       jrbarea.add(jrb6);
	       jrbarea.add(jrb7);
	       jrbarea.add(jrb8);
	       jrbarea.add(jrb9);
	       jrbarea.add(jrb10);
	      // jrbarea.setLayout(new GridLayout(10,1));//网格布局，给定行数和列数


	      
	       JPanel area=new JPanel();//增加面板
		   area.setBackground(Color.CYAN);//设置面板的背景颜色
			area.setLayout(new GridLayout(4,1));//网格布局，给定行数和列数
			//在面板上增加组件
			area.add(item1);
			area.add(item2);
		    area.add(jrbarea);

			File.add(area,BorderLayout.CENTER);//边框布局管理器
			
			
			//增加按钮
			Gbutton=new JButton("确定");
			Gbutton.setBackground(Color.pink);//设置按钮的颜色
			area.add(Gbutton);//在面板上添加按钮
			//动作监听器
			//File.add(area);
			Gbutton.addActionListener(new ActionListener()
		       {      
		           public void actionPerformed(ActionEvent event)
		           { 
		        	  if(jrb1.isSelected())
		        	  {
		        		  fileId = 1;
		        	  }
		        	  else if(jrb2.isSelected())
		        	  {
		        		  fileId = 2;
		        	  }
		        	  else if(jrb3.isSelected())
		        	  {
		        		  fileId = 3;
		        	  }
		        	  else if(jrb4.isSelected())
		        	  {
		        		  fileId = 4;
		        	  }
		        	  else if(jrb5.isSelected())
		        	  {
		        		  fileId = 5;
		        	  }
		        	  else if(jrb6.isSelected())
		        	  {
		        		  fileId = 6;
		        	  }
		        	  else if(jrb7.isSelected())
		        	  {
		        		  fileId = 7;
		        	  }
		        	  else if(jrb8.isSelected())
		        	  {
		        		  fileId = 8;
		        	  }
		        	  else if(jrb9.isSelected())
		        	  {
		        		  fileId = 9;
		        	  }
		        	  else if(jrb10.isSelected())
		        	  {
		        		  fileId = 10;
		        	  }
		        	 fileId--;
		        	  File.dispose(); //选择数据文件界面关闭
		        	  
		        	  
		        	  
		        	 list.clear();
	        	     int Gweight;
		        	 double Gvalue;
		        		  String filePath = "D:\\2021-2022\\大三下\\软件工程经济\\Git\\0-1\\0-1-knapsack\\res\\beibao"+fileId+".in";
		        	        FileInputStream fin = null;
							try {
								fin = new FileInputStream(filePath);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		        	        InputStreamReader reader = new InputStreamReader(fin);
		        	        BufferedReader buffReader = new BufferedReader(reader);
		        	       
						
		        		  String line;
		        		  int flag = 0;
		        		  wvsum = 0;
							
								try {
									while((line=buffReader.readLine())!= null)
									{
										String stringArray[] = line.split(" ");
										if(flag == 0)
										{
											 beibaosize = Integer.parseInt(stringArray[0]);
											 types = Integer.parseInt(stringArray[1]);
											 flag = 1;
										}
										else
										{
										  Gweight = Integer.parseInt(stringArray[0]);
										  Gvalue = Integer.parseInt(stringArray[1]);
										  w[wvsum] = Integer.parseInt(stringArray[0]);
										  v[wvsum] = Integer.parseInt(stringArray[1]);
										  wvsum++;
										  Goods goods = new Goods(Gweight,Gvalue);
										  list.add(goods);
										}
										 
									}
								} catch (NumberFormatException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							
							try {
								reader.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Twv.setText(null);
							 Twv.append(" 物品个数: " +types+"    "+" 背包容量: " +beibaosize+"\n");
							 int i = 1;
		        		  for (Goods tmp : list)
		        		   { 
		        			 //将给定的文本追加到文本区的已有文本的尾部
		        			  Twv.append("第"+i+"个物品： 重量: " +tmp.getWeight()+"   "+" 价值: " +tmp.getValue()+"\n");
		        			 i++;
		        	       } 
		        		  
		           }
		       });
			
			File.setTitle("0-1背包问题");//定义名称
			File.setBounds(190,60, 600,350);//定义位置及大小
			File.setVisible(true);
			File.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//关闭
			
			
			
	   }


}