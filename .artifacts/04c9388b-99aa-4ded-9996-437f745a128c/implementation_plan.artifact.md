# Implementation Plan - Edge-to-Edge Support

Enable edge-to-edge display support for all activities to comply with Android 15 requirements (SDK 35+).

## User Review Required

> [!IMPORTANT]
> Enabling edge-to-edge will make the status and navigation bars transparent/translucent. The app content will now draw behind these bars. I will apply insets to ensure critical UI elements (like the bottom AdView, FAB, and Top Toolbars) remain accessible and not obscured by system UI.

## Proposed Changes

### Dependencies

#### [MODIFY] [app/build.gradle](file:///D:/work/personal/Birdays/app/build.gradle)
* Add `androidx.activity:activity:1.13.0` to support `EdgeToEdge.enable()`.

### Activities

#### [MODIFY] [MainActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/MainActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.
* Apply window insets to `appBarLayout` (top padding) and `adView` (bottom padding) and `fab` (bottom margin).

#### [MODIFY] [DetailActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/DetailActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.
* Apply window insets to the root container.

#### [MODIFY] [EditActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/EditActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.
* Apply window insets.

#### [MODIFY] [HelpActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/HelpActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.

#### [MODIFY] [SettingsActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/SettingsActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.

#### [MODIFY] [LicensesActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/LicensesActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.

#### [MODIFY] [WidgetSettingsActivity.java](file:///D:/work/personal/Birdays/app/src/main/java/com/eblis/whenwasit/activities/WidgetSettingsActivity.java)
* Call `EdgeToEdge.enable(this)` in `onCreate`.

### Layouts

#### [MODIFY] [activity_main.xml](file:///D:/work/personal/Birdays/app/src/main/res/layout/activity_main.xml)
* Remove `fitsSystemWindows="true"` from `CoordinatorLayout` if necessary, or ensure it's handled correctly with the `AdView`.

## Verification Plan

### Automated Tests
* Run `./gradlew assembleDebug` to ensure compilation is successful.

### Manual Verification
* Deploy to a device/emulator running Android 15 (or targeting SDK 35+).
* Verify that:
    * The status bar and navigation bar are transparent.
    * The Toolbar is not overlapping with the status bar icons.
    * The AdView at the bottom is not covered by the navigation bar.
    * The FAB is correctly positioned above the AdView and Navigation Bar.
