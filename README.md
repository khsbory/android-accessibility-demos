# android-accessibility-demos

## 항목

### 팝업 화면 접근성 초점 문제

#### 문제점
<img src="https://user-images.githubusercontent.com/48876807/103151612-e835ce80-47c2-11eb-8082-51b95741ea44.png" width="200px"></img>
  + 팝업 화면을 dialog가 아닌 activity, fragment, layout등으로 구성할 경우 
  + 아이템을 클릭해 팝업 화면을 띄울때 접근성 초점이 팝업 화면이 아닌 기존의 화면에 머물러 있는 문제 발생
 

#### 데모 화면 구성
+ 리사이클러의 아이템을 클릭하면 팝업에 해당하는 view의 visibility 속성 변경해 팝업 구현   
<img src="https://user-images.githubusercontent.com/48876807/103150931-6d69b500-47bc-11eb-805d-aba5506c70d6.png" width="200px"></img>
<img src="https://user-images.githubusercontent.com/48876807/103150979-fd0f6380-47bc-11eb-83c3-d2558f43dae9.png" width="200px"></img>


#### 해결
+ 팝업 화면이 떴을때 기존 리사이클러뷰의 초점을 팝업화면의 닫기 버튼으로 이동
```
// 리사이클러뷰의 자식뷰 초점 없애기
// 터치 접근성 초점 설정
baseRView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
// 블루투스 키보드 초점 설정
baseRView.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
baseRView.isFocusable = false

// 닫기 버튼으로 초점 이동
// 터치 접근성 초점 설정
button.performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
// 블루투스 키보드 초점 설정
button.requestFocus()
```

+ 닫기 버튼을 클릭했을때 리사이클러뷰의 선택한 아이템으로 초점 이동
```
// 터치, 블루투스 키보드 초점 설정
// 리사이클러뷰의 자식뷰 초점 적용
baseRView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
baseRView.descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS

// 선택한 리사이클러뷰의 아이템으로 초점 이동
// 터치 접근성 초점 설정
baseRView.getChildAt(selectedPos).performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
// 블루투스 접근성 초점 설정
baseRView.getChildAt(selectedPos).requestFocus()
```



### 리스트에서 드래그앤드롭, 삭제 할 경우

#### 문제점
+ 리스트의 아이템을 삭제할 때, 접근성 초점이 위치해있는 아이템이 삭제되어 초점이 사라지는 문제점 발생
+ 리스트의 아이템을 드래그앤드롭으로 이동할 때, 드래그 버튼을 찾아 드래그해서 아이템을 이동하는 것이 불편
+ 툴바의 제목이 텍스트뷰가 아닌 이미지나 버튼으로 구성되는 경우 클릭 기능이 있는 것으로 오해


#### 데모 화면 구성
+ 툴바의 제목은 이미지뷰
+ 아이템 추가 버튼과 리사이클러뷰로 구성    
<img src="https://user-images.githubusercontent.com/48876807/103868360-252b8a80-510c-11eb-8bd4-ec2d1fde2da6.png" width="200px"></img>
  
  
#### 해결
+ 아이템이 삭제될 경우, 적절한 뷰로 포커스 이동
+ 아이템이 모두 삭제된 경우 추가 버튼으로 포커스 이동
+ 가장 마지막 아이템이 삭제된 경우 남아있는 가장 마지막 아이템의 content로 포커스 이동
+ 그외 일반적인 경우 삭제된 위치의 갱신된 아이템의 content로 포커스 이동
+ 아이템이 삭제되고 리사이클러뷰가 갱신되는데 시간이 걸리므로 delay를 0.2초 주고 초점 이동 수행

```
rViewAdapter.itemDeleteListener = object : DragListAdapter.OnItemDeleteListener{
            override fun onItemDelete(holder: DragListAdapter.ViewHolder, position: Int) {
                itemArr.removeAt(position)
                rViewAdapter.notifyItemRemoved(position)
                rViewAdapter.notifyItemRangeChanged(position, itemArr.size)

                //접근성 개선 코드
               Handler().postDelayed({
                   //아이템이 모두 삭제되면 추가 버튼으로 포커스 이동
                    if(rViewAdapter.itemCount == 0){
                        addButton.performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
                        addButton.requestFocus()
                    }
                    else{
                        //제일 마지막 아이템을 삭제한 경우 남아있는 가장 마지막 아이템의 content로 포커스 이동
                        if(position == rViewAdapter.itemCount){
                            (rView.get(position - 1) as ViewGroup).getChildAt(2).performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
                            (rView.get(position - 1) as ViewGroup).getChildAt(2).requestFocus()
                        }
                        //삭제된 위치의 새 아이템의 content로 포커스 이동
                        else{
                            (rView.get(position) as ViewGroup).getChildAt(2).performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
                            (rView.get(position) as ViewGroup).getChildAt(2).requestFocus()
                        }
                    } }, 200)
            }
        }
```

+ 아이템의 이동을 드래그앤드롭이 아닌 볼륨키 조절을 통해 수행
+ 볼륨키로 작업을 수행할 수 있도록 textView인 contentText를 seekBar로 변경 
```
//recyclerview adapter의 ViewHolder 내부

contentText.accessibilityDelegate = object : View.AccessibilityDelegate() {
                override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info?.className = SeekBar::class.java.name
                }

                //볼륨키 위 아래 눌렀을때 리스너 호출
                override fun performAccessibilityAction(host: View?, action: Int, args: Bundle?): Boolean {
                    if (action == AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD) {
                        itemMoveListener?.onItemMoveDown(adapterPosition)
                    }
                    else if (action == AccessibilityNodeInfo.ACTION_SCROLL_FORWARD) {
                        itemMoveListener?.onItemMoveUp(adapterPosition)
                    }
                    return super.performAccessibilityAction(host, action, args)
                }
            }
```
+ 볼륨키 위 아래 수행 동작 설정
+ 아이템의 content에 포커스가 있는 상태에서 볼륨키 누르면 위, 아래 방향으로 아이템 한칸씩 
+ 리사이클러뷰 갱신 시간 기다리기 위해 0.2초 delay
```
rViewAdapter.itemMoveListener = object : DragListAdapter.OnItemMoveListener{
    override fun onItemMoveUp(position: Int) {
        if(position - 1 >= 0){
            rViewAdapter.moveItem(position, position - 1)
            Handler().postDelayed({
                (rView.get(position - 1) as ViewGroup).getChildAt(2).performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
                (rView.get(position - 1) as ViewGroup).getChildAt(2).requestFocus()
                (rView.get(position - 1) as ViewGroup).getChildAt(2).announceForAccessibility(((rView.get(position) as ViewGroup).getChildAt(2) as TextView).text.toString()+ "위로 이동됨")
            }, 200)
        }
        else{
            (rView.get(position) as ViewGroup).getChildAt(2).announceForAccessibility("위로 이동할 수 없음")
        }
    }

    override fun onItemMoveDown(position: Int) {
        if(position + 1 < rViewAdapter.itemCount){
            rViewAdapter.moveItem(position, position + 1)
            Handler().postDelayed({
                (rView.get(position + 1) as ViewGroup).getChildAt(2).performAccessibilityAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS, null)
                (rView.get(position + 1) as ViewGroup).getChildAt(2).requestFocus()
                (rView.get(position + 1) as ViewGroup).getChildAt(2).announceForAccessibility(((rView.get(position) as ViewGroup).getChildAt(2) as TextView).text.toString()+ "아래로 이동됨")
            }, 200)
        }
        else{
            (rView.get(position) as ViewGroup).getChildAt(2).announceForAccessibility("아래로 이동할 수 없음")
        }
    }
}
```
+ 키보드 위, 아래 키로 아이템 이동 수행
```
//recyclerview adapter의 ViewHolder 내부

contentText.setOnKeyListener { v, keyCode, event ->
    if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN){
        itemMoveListener?.onItemMoveDown(adapterPosition)
        true
    }
    else if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN){
        itemMoveListener?.onItemMoveUp(adapterPosition)
        true
    }
    false
}
```

+ 툴바의 제목을 이미지뷰로 구성한 경우 contentDescription 속성으로 제목 설명, isEnabled 속성을 false로해서 비활성화됨 알려줌




### 커스텀 키보드 사용할 경우

#### 문제점
+ 톡백을 켰을때 기본 안드로이드 키보드에서 한번 클릭으로 키 선택 
+ 커스텀 키보드 사용할 경우 키보드가 버튼으로 구성되어 있으므로 더블 클릭하여 키를 선택해야 함


#### 데모 화면 구성
<img src="https://user-images.githubusercontent.com/48876807/105454908-07daec80-5cc6-11eb-8d72-37398d0cd87b.png" width="200px">     

  + recyclerview gridlayout으로 키보드 구성
  + 상단 탭 : Button 두개
  + 하단의 viewpager 내부에 fragment 두개 구성
 
 
#### 해결
+ 각 key button에 isTextEntryKey 속성 적용
: 해당 노드를 키보드 키로 변경
```
@RequiresApi(api = Build.VERSION_CODES.Q)
keyButton.accessibilityDelegate = object : View.AccessibilityDelegate() {
    override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
        super.onInitializeAccessibilityNodeInfo(host, info)
        info?.isTextEntryKey = true
    }
}
```

+ 키로 포커스 이동할때마다 리사이클러뷰의 몇행 몇열인지 읽어주는 음성 제거 위해 
```
keyBoardView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
```    
            
### 리사이클러뷰에서 스크롤 할 때 접근성 포커스 이동


#### 문제점
<img src="https://user-images.githubusercontent.com/48876807/111726416-46e38380-88ac-11eb-9d10-3f84387f3b65.png" width="200px">

  + 상단 리사이클러뷰를 클릭해 smoothScrollToPostion으로 스크롤 할 시 접근성 포커스가 해당 포지션으로 이동하지 않음
  + smoothScrollToPositon으로 스크롤 할 시 해당 포지션이 가장 하단으로 가게 스크롤 됨
  + 접근성 포커스의 순서가 화면에 보이는 순서(상단 리사이클러뷰 -> 하단 리사이클러뷰)가 아닌 상단 리사이클러뷰가 가장 마지막 순서


#### 데모 화면 구성
<img src="https://user-images.githubusercontent.com/48876807/111726737-d5580500-88ac-11eb-93cf-10038ff0c0f4.png" width="200px">   

  + 두개 리사이클러뷰 중첩 (front : 가로 리사이클러뷰, back : 세로 리사이클러뷰)
  + front 리사이클러뷰 아이템 선택시 해당 포지션으로 세로 리사이클러뷰 스크롤 이동


#### 해결
+ LinearLayoutManager의 smoothScrollToPosition, onScrollStateChanged 함수 오버라이드
  ```
  val manager: LinearLayoutManager = object : LinearLayoutManager(this, RecyclerView.VERTICAL, false) {

            // smoothScrollToPosition 함수를 호출했을때만 접근성 초점 이동하기 위해 flg 변수 설정
            private var flg = false
            private var targetPosition = 0

            // position의 아이템이 최상단으로 가도록 scroll
            override fun smoothScrollToPosition(view: RecyclerView, state: RecyclerView.State, position: Int) {
                flg = true
                val scroller = TopLinearSmoothScroller()
                scroller.targetPosition = position
                targetPosition = position
                startSmoothScroll(scroller)
            }

            override fun onScrollStateChanged(state: Int) {
                super.onScrollStateChanged(state)

                // smoothScrollToPosition 호출되고, 스크롤이 멈췄을때 접근성 포커스 targetPosition으로 이동
                if (state == SCROLL_STATE_IDLE && flg){
                    flg = false
                    val targetView = findViewByPosition(targetPosition + 1)
                    targetView?.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                    targetView?.requestFocus()
                }
            }
        }
  ```
  
+ 톡백 읽기 변경 (@@@작업을 하려면 두번 탭하세요)
  ```
  ViewCompat.replaceAccessibilityAction(textView, ACTION_CLICK, "@@@", null)
  ```
