# ExpensibleFabKotlin
Expensible fab button to handle multiple options for fab like googlemaps

[![screen](https://raw.githubusercontent.com/alexMasson58/ExpensibleFabKotlin/master/medias/screen.png)](https://github.com/alexMasson58/ExpensibleFabKotlin)

### Example of screens you can build with ShapeOfView :

[![screen](https://raw.githubusercontent.com/alexMasson58/ExpensibleFabKotlin/master/medias/presentation.gif)](https://github.com/alexMasson58/ExpensibleFabKotlin)


# Download


[ ![Download](https://api.bintray.com/packages/alexmasson58/maven/expensiblefabkotlin/images/download.svg) ](https://bintray.com/alexmasson58/maven/expensiblefabkotlin)
```java
dependencies {
    implementation 'com.alexmasson58.expensiblefabkotlin:expensiblefabkotlin:(lastest version)'
}
```

# Attributes & Classes
exp_fab_autoclose : default true, set is the fab must close after a click on an Option.
exp_fab_closed_icon : reference to a drawable, to display another icon than selected option when closed.

```xml
 <com.alexmasson58.expensiblefabkotlin.LinearExpensibleFAB
        android:id="@+id/closedIcon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:exp_fab_closed_icon="@drawable/ic_baseline_cake_24px"
        app:layout_constraintBottom_toTopOf="@+id/tvautoclosefalse"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tvClosedIcon" />
```

Option : implementation of a fab Option, simply an icon and the code to execute on click.
```java
 class Option(var iconId: Int = -1, var returnCode: () -> Unit)
```
# Credits

Florent Champigny [http://www.florentchampigny.com/](http://www.florentchampigny.com/) for his fork and implementation with canvas.

Author: Alexandre MASSON

Blog : [http://www.tutos-android-france.com/](http://www.tutos-android-france.com/)

# License
--------

    Copyright 2017 alexMasson58, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


