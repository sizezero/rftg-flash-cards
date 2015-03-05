package org.kleemann.rftg_flash_cards

import org.scaloid.common._

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

import android.content.Intent
import android.net.Uri
import android.graphics.Color
import android.os.{Bundle, Environment}
import android.provider.MediaStore
import android.util.Log
import android.view.{View, ViewGroup, Menu, MenuItem, Gravity}
import android.widget.{FrameLayout, Toast}

class Main extends SActivity  {

  onCreate {
    contentView = new SFrameLayout {

      this += new SVerticalLayout {

        this += new SLinearLayout {
          STextView("12/55").wrap
          SButton("Restart").wrap.onClick { toast("Restart") }
        }

        SButton("Tile").textSize(24.5 sp).onClick { toast("Tile") }

        this += new SLinearLayout {
          SButton("Success").wrap.onClick { toast("Success") }
          SButton("Failure").wrap.onClick { toast("Failure") }
        }
      }

    } padding 20.dip
  }

}
