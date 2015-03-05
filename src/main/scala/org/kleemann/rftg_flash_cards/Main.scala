package org.kleemann.rftg_flash_cards

import org.scaloid.common._

// for 'sp' and other postfix ops
import scala.language.postfixOps

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

  var successButton: SButton = null
  var failButton: SButton = null

  onCreate {
    contentView = new SFrameLayout {

      this += new SVerticalLayout {

        this += new SLinearLayout {
          STextView("12/55").wrap
          SButton("Restart").wrap.onClick {
            toast("Restart")
            setTileSelected(false)
          }
        }

        SButton("Tile").textSize(24.5 sp).onClick {
          toast("Tile")
          setTileSelected(true)
        }

        this += new SLinearLayout {
          successButton = new SButton("Success")
          successButton.wrap.onClick { toast("Success") }
          this += successButton

          failButton = new SButton("Fail")
          failButton.wrap.onClick { toast("Fail") }
          this += failButton

        }
      }

    } padding 20.dip

    setTileSelected(false)
  }

  var isTileSelected: Boolean = false
  def setTileSelected(b: Boolean) {
    isTileSelected = b
    successButton.enabled(isTileSelected)
    failButton.enabled(isTileSelected)
  }
}
