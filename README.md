[![Hex.pm](https://img.shields.io/badge/Jetpack-AndroidX-orange)]() [![](https://jitpack.io/v/iDeMonnnnnn/DeMon-ARA.svg)](https://jitpack.io/#iDeMonnnnnn/DeMon-ARA)
## DeMon-ARA
Activity Results API自动化注册&极简使用方案。
<https://demon.blog.csdn.net/article/details/123276161>

### 添加依赖

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

[latest_version](https://github.com/iDeMonnnnnn/DeMon-ARA/releases)
##### Kotlin
```
   implementation 'com.github.iDeMonnnnnn.DeMon-ARA:core-ktx:$latest_version'
   implementation 'androidx.activity:activity-ktx:1.4.0'
   implementation 'androidx.fragment:fragment-ktx:1.4.1'
````

##### Java
```
   implementation 'com.github.iDeMonnnnnn.DeMon-ARA:core:$latest_version'
   implementation 'androidx.activity:activity:1.4.0'
   implementation 'androidx.fragment:fragment:1.4.1'
```

### 使用

#### 1.初始化

```java
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DeMonAraHelper.init(this)
    }
}
```

#### 2.Kotlin代码中使用

```java
val intent = Intent(this@MainActivity, JavaActivity::class.java)
forActivityResult(intent) {
     val str = it?.getStringExtra("tag") ?: ""
     text.text = "跳转页面返回值：$str"
}
```

#### 3.Java代码中使用

```java
    DeMonActivityResult<Intent, ActivityResult> result = DeMonAraHelper.getActivityResult(JavaActivity.this);
            if (result != null) {
        result.launch(new Intent(this, TestJumpActivity.class), true,
                data -> {
                    if (data.getData() != null) {
                        String str = data.getData().getStringExtra("tag");
                        binding.text.setText("跳转页面返回值：" + str);
                    } else {
                        binding.text.setText("我是返回键返回的，没有返回值~");
                    }
                });
    }
```

### Licensed

```
   Copyright [2022] [DeMon-ARA]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```