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

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DeMonAraHelper.init(this)
    }
}
```

#### 2.Kotlin代码中使用

kotlin的进一步封装使用可见[ActivityResultApi.kt](https://github.com/iDeMonnnnnn/DeMon-ARA/blob/main/core-ktx/src/main/java/com/demon/corektx/ActivityResultApi.kt)

```kotlin
val intent = Intent(this@MainActivity, JavaActivity::class.java)
forActivityResult(intent) {
     val str = it?.getStringExtra("tag") ?: ""
     text.text = "跳转页面返回值：$str"
}
```

#### 3.Java代码中使用

```kotlin
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

更多使用，可见[示例代码](https://github.com/iDeMonnnnnn/DeMon-ARA/tree/main/app/src/main/java/com/demon/ara)

可直接下载体验[demo apk](https://github.com/iDeMonnnnnn/DeMon-ARA/raw/main/demo.apk)

### Benchmark

我们简单测试一下以下四种方式直接执行100次时的性能。
测试代码可见：[BenchmarkActivity.kt](https://github.com/iDeMonnnnnn/DeMon-ARA/blob/main/app/src/main/java/com/demon/ara/BenchmarkActivity.kt)
测试机型：小米5

|方式|1(ms)|2(ms)|3(ms)|4(ms)|5(ms)|
|--|--|--|--|--|--|
|startActivityForResult()/onActivityResult()|6638|6638|6605|6597|6687|
|GhostFragment|6589|6752|6555|6553|6572|
|Activity Results API|6586|6786|6708|6666|6604|
|DeMon-ARA|6974|6851|6901|6912|6839|

内存方面：测试过程中使用AndroidStudio Profiler监测的内存波动基本一致。

### BUG/问题/建议

请[issues](https://github.com/iDeMonnnnnn/DeMon-ARA/issues)

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