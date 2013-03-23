import java.util.Scanner;
import java.util.ArrayList;

class Node {
 Node parentId = null;
 char ch = ' ';
 int freq = 0;
 String code = "";
 
  public Node(char ch,int n) {
   this.ch = ch;
   this.freq = n;   
  }
  
  public String getCode() {
    if(parentId==null) return "";
	return parentId.getCode()+code; 
  }
 }
 
 class Huffman {
 
  public static void sort(Node[] node) {
    for(int i=0;i<node.length-1;i++) 
	 for(int j=i+1;j<node.length;j++)
	  if(node[j].freq > node[i].freq ) {
	  Node temp = node[i];
	  node[i] = node[j];
	  node[j] = temp;
	}
  }
  
  public static void insert(Node[] node,Node nod) {
     int i=0;
    for(i=0;i<node.length-1;i++)
	 if(node[i].freq<nod.freq) break;
	  for(int j=node.length-1;j>i;j--) 
	   {node[j]=node[j-1];}
	  node[i] = nod; 	 
  }
  
  public static int nos(Node[] node) {
   int count = 0;
   for(int i=0;i<node.length;i++)
    if(node[i].parentId==null) count++;
   return count;	
  }
  
  public static void println(String str) {
   System.out.println(""+str);
  }
  
  public static void print(String str) {
   System.out.print(""+str);
  }
  
  public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);   
   print("Enter a string:");
  String str = sc.next();
  char[] chr = new char[str.length()];
  int[] nchar = new int[str.length()];
  int chf = 0;
  for(int i=0;i<str.length();i++) {
   int nch = 0;
   boolean dist = true;
   for(int k=0;k<i;k++)
    if(str.charAt(i)==chr[k]) dist = false ;
   if(dist)
   for(int j=i;j<str.length();j++) {
    if(str.charAt(i)==str.charAt(j)) nch++;
	}
   else
     continue;   
   chr[chf] = str.charAt(i);	
   nchar[chf++] = nch;
  }     
  Node[] node = new Node[chf];
   for(int i=0;i<chf;i++){     
      node[i] = new Node(chr[i],nchar[i]);	  
	}
	Node[] org = new Node[chf];	
   sort(node);   
   for(int i=0;i<chf;i++){           
	  org[i] = node[i];
	}
   for(int i=0;i<chf;i++) 
    println(node[i].ch+" = "+node[i].freq);  
  int k=chf-1,j=chf-2;
  while (k!=0)  {  
  if(k==0) break;
  println(j+" "+k);
    if(node[j].freq < node[k].freq)  {
	  node[j].code+="0";
	  node[k].code+="1";
	}
	else {
	  node[j].code+="1";
	  node[k].code+="0";
	}
	Node nod = new Node('\0',node[j].freq+node[k].freq);
	node[j].parentId = nod;
	node[k].parentId = nod;
    if(node[j].ch!='\0'){
	 for(int i=0;i<org.length;i++) if(org[i].ch==node[j].ch) org[i]=node[j];
	}
    if(node[k].ch!='\0'){
	 for(int i=0;i<org.length;i++) if(org[i].ch==node[k].ch) org[i]=node[k];
	}	
	for(int i=0;i<node.length;i++) 
	println(node[i].ch+" = "+node[i].freq);
	println("\n");
	insert(node,nod);					
	for(int i=0;i<node.length;i++) 
    println(node[i].ch+" = "+node[i].freq);	
	j--;k--;
  }
  for(int i=0;i<org.length;i++) {
    println("Charecter "+org[i].ch+" : "+org[i].getCode());
  }
 }
 }