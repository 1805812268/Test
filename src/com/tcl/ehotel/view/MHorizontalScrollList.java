package com.tcl.ehotel.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class MHorizontalScrollList extends FrameLayout {
	/**
	 * width of child
	 */
	public int childWidth ;
	/**
	 * height of child
	 */
	public int childHeight;
	public int leftOverlap;
	public int rightOverlap;
	/**
	 * gap between child
	 */
	public int gap;
	/**
	 * per seconds
	 */
	public float animationTime = 0.5f;
	
	private Scroller mScroller=null;
	
	private int currentChildIndex =0;
	private boolean isMoving = false;

	private int maxVisbleChild= 0;
	private int middleChildIndex =0;
	private Context mContext ;
	
	private Adapter mAdapter = null;
	
	public MHorizontalScrollList(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public MHorizontalScrollList(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public MHorizontalScrollList(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}
	public int getCurrentIndex(){
		return currentChildIndex ;
	}
	/**
	 * 
	 * @return
	 */
	public android.view.ViewGroup.LayoutParams getParams() {
		android.view.ViewGroup.LayoutParams params = getLayoutParams();
		int width =(childWidth-rightOverlap +gap)*maxVisbleChild+rightOverlap-gap ;
		if(params == null){
			params =new android.view.ViewGroup.LayoutParams(width,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		}else{
			params.width = width;
		}
		return params;
	}
	/**
	 * 
	 * @return
	 */
	public android.view.ViewGroup.LayoutParams getParamsByChild() {
		android.view.ViewGroup.LayoutParams params = getLayoutParams();
		int childNum = getChildCount();
		int width = 0 ;
		if(childNum>=maxVisbleChild){
			FrameLayout.LayoutParams tempparams= (LayoutParams) getChildAt(childNum-1).getLayoutParams();
			width = getChildAt(maxVisbleChild-1).getPaddingLeft()+tempparams.leftMargin+tempparams.width;
		}else{
			FrameLayout.LayoutParams tempparams= (LayoutParams) getChildAt(childNum-1).getLayoutParams();
			width = getChildAt(childNum-1).getPaddingLeft()+tempparams.leftMargin+tempparams.width;
		}
		//width =         (childWidth-rightOverlap +gap)*maxVisbleChild+rightOverlap-gap ;
		if(params == null){
			params =new android.view.ViewGroup.LayoutParams(width,android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		}else{
			params.width = width;
		}
		return params;
	}
	private  void init(){
		this.setBackgroundColor(Color.TRANSPARENT);
		mScroller =new Scroller(mContext);
		currentChildIndex =0;
	}
	public void setAdapter(Adapter adapter){
		mAdapter = adapter;
		removeAllViews();
		currentChildIndex =0;
		scrollTo(0, 0);
		if(!update()){
			return ;
		};
		this.setLayoutParams(getParamsByChild());
//		this.getChildAt(currentChildIndex).requestFocus();
//		this.getChildAt(currentChildIndex).requestFocusFromTouch();
	}
	public Adapter getAdapter(){
		return mAdapter;
	}
	
	private boolean update(){
		if(mAdapter == null||mAdapter.getCount()==0){
			return false;
		}
		int childNum = mAdapter.getCount();
		int startX =0;
		for(int i = 0;i<childNum;i++){
			View child = mAdapter.getView(i, null, null);
			FrameLayout.LayoutParams params =(FrameLayout.LayoutParams) child.getLayoutParams();//new FrameLayout.LayoutParams(childWidth,childHeight); 
			params.leftMargin =startX ;// i*(childWidth-rightOverlap+gap);
			startX +=params.width-rightOverlap+gap;
			this.addView(child, params);
			child.setFocusable(true);
			child.setFocusableInTouchMode(true);
			child.setOnClickListener(new MyOnClickListener(child,i));
			child.setOnFocusChangeListener(new MyOnFocusChangeListener(child,i));
		}
		return true;
	}
	
	
	public int getPositionInVisibleChild(){
		return currentChildIndex -visibleChildIndex;
	}
	public int getPositionOfFirstVisibleChild(){
		return visibleChildIndex;
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			scrollLast();
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			scrollNext();
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		return super.onKeyUp(keyCode, event);
	}
	/**
	 * scroll down one child
	 * @return
	 */
	protected boolean scrollNext(){
		if(mScroller.computeScrollOffset()){// last scrolling not finish
			//return false;
			stopMoveToTarget();
		}
		if(visibleChildIndex>0){
			scroll(-1);
		}
		return false ;
	}
	/**
	 * 
	 * @param num
	 * @return
	 */
	protected boolean scrollNext(int num){
		if(mScroller.computeScrollOffset()){// last scrolling not finish
			//return false;
			stopMoveToTarget();
		}
		if(visibleChildIndex>0){
			scroll(-1*num);
		}
		return false ;
	}
	/**
	 * set max show number of visible child
	 * @param max
	 */
	public void setMaxVisbleChild( int max){
		maxVisbleChild =  max;
		middleChildIndex = maxVisbleChild/2;
	}
	/**
	 * scroll up on child
	 * @return
	 */
	protected boolean scrollLast(){
		if(mScroller.computeScrollOffset()){// last scrolling not finish
			//return false;
			stopMoveToTarget();
		}
		int showNum = maxVisbleChild;
		if(showNum<getChildCount()){
			if(visibleChildIndex+showNum<getChildCount()){
				scroll(1);
			}
		}
		return true;
	}
	/**
	 * 
	 * @return
	 */
	protected boolean scrollLast(int num){
		if(mScroller.computeScrollOffset()){// last scrolling not finish
			//return false;
			stopMoveToTarget();
		}
		int showNum = maxVisbleChild;
		if(showNum<getChildCount()){
			if(visibleChildIndex+showNum<getChildCount()){
				scroll(1*num);
			}
		}
		return true;
	}
	/**
	 * 
	 * @param num
	 */
	private void scroll(int num){
		int startX = getScrollX();
		int startY = getScrollY();
		//visibleChildIndex += num;
		int scrollWidth = 0;
		if(num>=0){
			for(int i=visibleChildIndex;i<visibleChildIndex+num;i++ ){
				scrollWidth+=this.getChildAt(i).getWidth()-rightOverlap+gap;
			}
		}else{
			for(int i=visibleChildIndex-1;i>=visibleChildIndex+num;i-- ){
				scrollWidth += this.getChildAt(i).getWidth()-rightOverlap+gap;
			}
			scrollWidth =scrollWidth*-1;
		}
		visibleChildIndex += num;
		mScroller.startScroll(startX, startY, scrollWidth/*(childWidth-rightOverlap+gap)*num */,0, (int)(1000*animationTime/*Math.abs(num)*/));
		isMoving = true;
		if(mOnScrollListener !=null){
			mOnScrollListener.onStart();
		}
		invalidate();
	}
	
	@Override
	public void computeScroll() {
		// return true if scroll not finish 
		if(mScroller.computeScrollOffset()){
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}else{
			if(isMoving){
				isMoving = false;
				if(mOnScrollListener != null){
					mOnScrollListener.onFinish();
				}
			}
		}
		//super.computeScroll();
	}
	//停止滑屏,立即到达目标
	/**
	 * stop scroll ,and move to target
	 */
	public void stopMoveToTarget(){
		if(mScroller != null){
			if(!mScroller.isFinished()){
				mScroller.abortAnimation();
				scrollTo(mScroller.getFinalX(),mScroller.getFinalY());
				mScroller.forceFinished(true);
				isMoving = false;
				if(mOnScrollListener !=null){
					mOnScrollListener.onStart();
				}
				
			}
	    }else{
	    }
	}
	
	private int lastFocusChildIndex = 0 ;
	private int visibleChildIndex =0;
	
	private OnItemClickListener mOnItemClickListener =null;
	private OnItemFocusChangeListener mOnItemFocusChangeListener = null;
	private OnItemHoverListener mOnItemHoverListener = null;
	private OnScrollListener mOnScrollListener =null;
	
	public interface OnScrollListener{
		public void onStart();
		public void onFinish();
	}
	
	public interface OnItemClickListener{
		public void onItemClick(View view ,int pos);
	}
	public interface OnItemHoverListener{
		public void onHover(int pos);
	}
	public interface OnItemFocusChangeListener{
		public void onItemFocusChanged(View view,int pos,boolean isFocus);
	}
	public void setOnItemFocusChangedListener(OnItemFocusChangeListener listener){
		mOnItemFocusChangeListener = listener;
	}
public void setOnItemClickListener(OnItemClickListener listener){
		mOnItemClickListener = listener ;
	}
	public void setOnScrollListener(OnScrollListener listener){
		mOnScrollListener = listener;
	}
	
	
	
	
	class MyOnClickListener implements OnClickListener{
		private View view ;
		private  int pos ;
		public MyOnClickListener(View view ,int pos){
			this.view = view ;
			this.pos = pos ;
		}
		@Override
		public void onClick(View args) {
			view.requestFocus();
			view.requestFocusFromTouch();
			if(mOnItemClickListener != null){
				mOnItemClickListener.onItemClick(view,pos);
			}
		}
	}
	class MyOnFocusChangeListener implements OnFocusChangeListener{
		private View view ;
		private int pos;
		public MyOnFocusChangeListener(View view ,int pos){
			this.view = view ;
			this.pos = pos;
		}
		@Override
		public void onFocusChange(View args, boolean isFocus) {
			if(isFocus){
				currentChildIndex = pos;
				view.requestFocus();
				view.requestFocusFromTouch();
				//view.setBackgroundColor(Color.RED);
			}else{
				//view.setBackgroundColor(Color.GREEN);
			}
			if(isFocus){
				autoScroll(pos);
			}
			if(mOnItemFocusChangeListener != null){
				mOnItemFocusChangeListener.onItemFocusChanged(view,pos,isFocus);
			}
			if (isFocus) {
				lastFocusChildIndex = pos;
			}
		}
	}
	private void autoScroll(int pos){
		int childNum = getChildCount();
		if(childNum <=maxVisbleChild){
			return ;
		}
		if(pos<(middleChildIndex+visibleChildIndex)){//above middle position
			int tempgap = (middleChildIndex+visibleChildIndex) -pos;
			int hide = visibleChildIndex;
			if(tempgap<=hide){
				scrollNext(tempgap);
			}else{
				scrollNext(hide);
			}
		}else if(pos==(middleChildIndex+visibleChildIndex)){// at middle position
			//TODO  do noting
		}else{ // below middle position
			int tempgap =  pos - (middleChildIndex+visibleChildIndex);
			int hide = childNum-(maxVisbleChild+visibleChildIndex);
			if(tempgap<=hide){
				scrollLast(tempgap);
			}else{
				scrollLast(hide);
			}
		}
	}
}
