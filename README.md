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


