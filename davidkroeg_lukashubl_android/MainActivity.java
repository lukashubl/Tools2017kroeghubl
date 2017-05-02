package at.fhhgb.mc.Aufgabe03;


public class DoubleLinkedList {
	/** Pointer to the first and last element of the list */
    private DLNode head, tail;
    
    //================================================================================
    // METHODS FROM DOUBLELINKEDLIST AUFGABE 03
    //================================================================================
    /** Adds another list at the beginning of this linked list.
    * If the first element is an int-node, a new node is prepended. * If the first element is a list-node, the _list is inserted
    * recursively into that list. */
    public void pushFrontRecursive(DoubleLinkedList _list) {
    	if(_list !=null){
    		if(this.getHead()==null || this.getHead().getVal() != Integer.MIN_VALUE && this.getHead().getList() == null){	//push list-node
        		this.pushFrontListNode(_list);
        	}else if(this.getHead().getVal() == Integer.MIN_VALUE && this.getHead().getList() != null){	//recursion
        		this.getHead().getList().pushFrontRecursive(_list);
        	}
    	}else System.out.println("given list is empty - pushFrontRecursive(DoubleLinkedList _list");
    	
    }
    /** Adds another list at the end of this linked list.
    * If the last element is an int-node, a new node is appended. * If the last element is a list-node, the _list is inserted
    * recursively into that list. */
    public void pushBackRecursive(DoubleLinkedList val) {
    	if(val !=null){
    		if(this.getTail()==null || this.getTail().getVal() != Integer.MIN_VALUE && this.getTail().getList() == null){	//push list-node
        		this.pushBackListNode(val);
        	}else if(this.getTail().getVal() == Integer.MIN_VALUE && this.getTail().getList() != null){		//recursion
        		this.getTail().getList().pushBackRecursive(val);
        	}
    	}else System.out.println("given list is empty - pushBackRecursive(DoubleLinkedList val");
    	
    }
    /** Adds an list-element-node at the front of the linked list. HELPER FOR PUSHFRONTRECURSIVE*/
    private void pushFrontListNode(DoubleLinkedList dll) {
    	if(dll !=null){
    		DLNode newNode = new DLNode(dll);
        	if(this.getHead() == null){ //list is empty
        		this.setHead(newNode);
        		this.setTail(newNode);
        	}else{	//list is not empty
        		newNode.setNext(this.getHead());
            	this.getHead().setPrev(newNode);
            	this.setHead(newNode);
        	}
    	}else System.out.println("given list is empty - pushFrontListNode(DoubleLinkedList dll");
    	
    }
    /** Adds an list-element-node at the end of the linked list. HELPER FOR PUSHBACKRECURSIVE*/
    private void pushBackListNode(DoubleLinkedList dll) {
    	if(dll!=null){
    		DLNode newNode = new DLNode(dll);
        	if(this.getTail() == null){	//List is empty
        		this.setHead(newNode);
        		this.setTail(newNode);
        	}else{	//list is not empty
            	this.getTail().setNext(newNode);
            	newNode.setPrev(this.getTail());
            	this.setTail(newNode);
        	}
    	}else System.out.println("given list is empty - pushBackListNode(DoubleLinkedList dll");
    	
    }
    /** Removes and returns the front element of the linked list, or if
    * the first element is another list, returns that list’s first
    * element. Returns Integer.MIN_VALUE if empty */
    public int popFront() {
    	if(this.getHead() == null){	//list is empty
    		return Integer.MIN_VALUE;
    	}else{	//list is not empty
    		if(this.getHead().getVal() == Integer.MIN_VALUE && this.getHead().getList() != null){	//recursion
    			return this.getHead().getList().popFront();
    		}else if(this.getHead().getVal() != Integer.MIN_VALUE && this.getHead().getList() == null){
    			int retVal = this.getHead().getVal();
        		this.setHead(this.getHead().getNext());
        		if(this.getHead()!=null)this.getHead().setPrev(null);
        		return retVal;
    		}else return 0;		//in this case, there must be an error, this case must not happen	
    	}
    }
    /** Returns the front element of the list without removing it. If the 
     * * first element is another list, returns that list’s first element 
     * * Returns Integer.MIN_VALUE if empty */
    public int peekFront() {
    	if(this.getHead() == null){		//list is empty
    		return Integer.MIN_VALUE;
    	}else if(this.getHead().getVal()!=Integer.MIN_VALUE && this.getHead().getList() == null){	//there is an element at the beginning
    		return this.getHead().getVal();
    	}else if(this.getHead().getVal()==Integer.MIN_VALUE && this.getHead().getList() != null){	//recursion
    		return this.getHead().getList().peekFront();	
    	}else return 0;		//this case will never happen
    }
    /** Removes and returns the element from the back of the linked list, * 
     * or if the last element is another list, returns that list’s last * element Returns Integer.MIN_VALUE if empty */
    public int popBack() {
    	if(this.getTail()==null){	//list is empty
    		return Integer.MIN_VALUE;
    	}else if(this.getTail().getVal() != Integer.MIN_VALUE && this.getTail().getList() == null){	//last element is a value
    		int retVal = this.getTail().getVal();
			this.setTail(this.getTail().getPrev());
			if(this.getTail()!=null)this.getTail().setNext(null);
			return retVal;
    	}else if(this.getTail().getVal() == Integer.MIN_VALUE && this.getTail().getList() != null){
    		return this.getTail().getList().popBack();
    	}else return 0;	//this case can not happen
    }
    /** Returns the element at the back of the list without removing it. * If the last element is another list, returns that list’s last
    * element Returns Integer.MIN_VALUE if empty */
    public int peekBack(){
    	if(this.getTail() == null){	//list is empty
    		return Integer.MIN_VALUE;
    	}else if(this.getTail().getVal() != Integer.MIN_VALUE && this.getTail().getList() == null){	//last element is a value
    		return this.getTail().getVal();
    	}else if(this.getTail().getVal() == Integer.MIN_VALUE && this.getTail().getList() != null){	//last element is a list
    		return this.getTail().getList().peekBack();
    	}else return 0; //This case can never happen
    }
    /** Returns the number of elements in the double linked list and of * all its sub-lists */
    public int elements() {
    	int ctr = 0;
    	DLNode element = this.getHead();
    	
    	while(element != null){
    		if(element.getVal()!=Integer.MIN_VALUE && element.getList()==null){
    			ctr += 1;
    		}else{
    			ctr += element.getList().elements();	//recursive call with sub-list
    		}
    		element = element.getNext();
    	}
    	return ctr;
    }
    /** Reverse the order of all elements. Does NOT change the order of * sub-lists! */
    public void reverse() { 
		if(this.getHead() != null && this.getHead() != this.getTail()){	// head == tail --> either no or just one element in the list --> change is senseless
			DLNode x = this.getHead();
			DLNode y = this.getHead();
			DLNode hlp = this.getHead();
			while(y!=null){
				y = y.getNext();
				x.setNext(x.getPrev());
				x.setPrev(y);
				x=y;
			}
			this.setHead(this.getTail());
			this.setTail(hlp);
		}
	}
    /**
    * Returns true if the element val exists in the list or in any of * its sub-lists, false otherwise. */
    public boolean search(int val) {
    	DLNode ref = this.getHead();
    	while(ref!=null){
    		if(ref.getVal() == Integer.MIN_VALUE && ref.getList() != null){	//its a list
    			return ref.getList().search(val);
    		}else if(ref.getVal() != Integer.MIN_VALUE && ref.getList() == null){	//its a value
    			if(ref.getVal() == val) return true;
    		}
    		ref = ref.getNext();
    	}
    	return false;
    }
    //================================================================================
    // METHODS FROM DOUBLELINKEDLIST AUFGABE 02
    //================================================================================
    /** Constructor initializes list with a standard size.*/
    public DoubleLinkedList() {
    	this.setHead(null);
    	this.setTail(null);
    }
    /**
    * Copy constructor initializes list with another list.
    * This constructor must COPY all elements of the other list. * The elements of the other list must NOT be changed!
    */
    public DoubleLinkedList(DoubleLinkedList other) {
    	this.setHead(null);
    	this.setTail(null);
    	this.pushFront(other);
    }
    /**
    * Deinitializes the object; think about it and comment what to do here. */
    protected void finalize() {
    	//loose track of the list
    	this.setHead(null);
    	this.setTail(null);
    }
    /**
    * Adds all elements from another list at the front of this linked list. */
    public void pushFront(DoubleLinkedList other) {
    	if(other !=null){
    		DLNode elements = other.getTail();
    		while(elements!=null){
    			if(elements.getVal() != Integer.MIN_VALUE){
    				this.pushFront(elements.getVal());
    			}else{
    				DoubleLinkedList listNode = new DoubleLinkedList();
    				listNode.pushFront(elements.getList());
    				this.pushFrontRecursive(listNode);
    			}
    			elements = elements.getPrev();
    		}
    	}else System.out.println("given list is null - pushFront(DoubleLinkedList other)");	
    }
    /**
    * Adds all elements from another list at the back of this linked list. */
    public void pushBack(DoubleLinkedList other) {
    	if(other !=null){
    		DLNode elements = other.getHead();
    		while(elements!=null){
    			if(elements.getVal() != Integer.MIN_VALUE){
    				this.pushBack(elements.getVal());
    			}else{
    				DoubleLinkedList listNode = new DoubleLinkedList();
    				listNode.pushBack(elements.getList());
    				this.pushBackRecursive(listNode);
    			}
    			elements = elements.getNext();
    		}
    	}else System.out.println("given list is null - pushBack(DoubleLinkedList other");
    }
    /** Clones this DoubleLinkedList instance and returns an exact COPY.*/
    public DoubleLinkedList clone() {
    	DoubleLinkedList returnList = new DoubleLinkedList(this);
    	return returnList;
    }
    /**
    * Returns true if the other list is equal to this one, false otherwise. * The contents of the two lists must not be changed!
    */
    public boolean equals(DoubleLinkedList other) {
    	if(other !=null && this!= null){
    		DLNode x = this.getHead();
        	DLNode y = other.getHead();
        	while(x!=null && y!=null){
        		if(x.getList()!=null && y.getList()!=null){
        			boolean recFunCall = true;
        			recFunCall = x.getList().equals(y.getList());	//2 list nodes --> recursive call
        			if(recFunCall == false) return false;
        		}     			
        		if(x.getVal() != y.getVal()) return false;		//2 val-nodes are different
        		x = x.getNext();
        		y= y.getNext();
        	}
        	return true;
    	}
    	return false;	
    }

    /** Returns a string representation of the list. See the exercise * specification below for the exact string format! */
    public String toString() {
    	StringBuilder stringB = new StringBuilder();
    	DLNode jumpingNode = this.getHead();
    	while(jumpingNode !=null){
    		if(jumpingNode.getList() !=null){
    			stringB.append("[");
    			stringB.append(jumpingNode.getList().toString());
    			stringB.append("]");
    		}else{
    			stringB.append(jumpingNode.getVal());
    			if(jumpingNode.getNext() != null && jumpingNode.getNext().getList()==null) stringB.append("-");
    		}
    		jumpingNode = jumpingNode.getNext();
    	}
    	return stringB.toString();
    }
    
    //================================================================================
    // GETTER & SETTER
    //================================================================================
    public DLNode getHead(){
    	return this.head;
    }
    public DLNode getTail(){
    	return this.tail;
    }
    private void setHead(DLNode node){
    	this.head = node;
    }
    private void setTail(DLNode node){
    	this.tail = node;
    }

    //================================================================================
    // METHODS FROM DOUBLELINKEDLIST PACKAGE AUFGABE 01
    //================================================================================
    /** Clears all elements from the linked list */
    public void clear() {
    	this.setHead(null);
    	this.setTail(null);
    }
    /** Adds an element at the front of the linked list.*/
    public void pushFront(int val) {
    	DLNode newNode = new DLNode(val);
    	if(this.getHead() == null){ //list is empty
    		this.setHead(newNode);
    		this.setTail(newNode);
    	}else{	//list is not empty
    		newNode.setNext(this.getHead());
        	this.getHead().setPrev(newNode);
        	this.setHead(newNode);
    	}
    }
    /** Adds an element at the back of the linked list. */
    public void pushBack(int val) {
    	DLNode newNode = new DLNode(val);
    	if(this.getTail() == null){	//List is empty
    		this.setHead(newNode);
    		this.setTail(newNode);
    	}else{	//list is not empty
        	this.getTail().setNext(newNode);
        	newNode.setPrev(this.getTail());
        	this.setTail(newNode);
    	}
        //added for git
        if (val == 0) return;
    	
    }
    public void anotherUnusedMethod(){
        System.Out.println("David Krög is pretty poor at Dota 2");
        int number = 0;
        if(number == 0)System.Out.println("He is even worse than expected before ^^");
    }
    //test method for git
    public void thisIsANewMethod() {
        string newString = "Line of code from Member1.";
        string s = "Needed to fix this bug";
    }
}
