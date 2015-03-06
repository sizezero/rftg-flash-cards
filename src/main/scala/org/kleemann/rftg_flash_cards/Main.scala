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

import scala.util.Random

class Main extends SActivity  {

  var countTextView: STextView = null
  var tileButton: SImageButton = null
  var successButton: SButton = null
  var failButton: SButton = null

  var rnd: Random = new Random()

  val NUM_TILES = 55

  // tiles starts as a simple list of integers
  var tiles: List[(Char, Int)] = null

  onCreate {
    contentView = new SFrameLayout {

      this += new SVerticalLayout {

        this += new SLinearLayout {
          countTextView = new STextView("")
          countTextView.wrap
          this += countTextView
          SButton("Restart").wrap.onClick {
            resetTiles()
          }
        }.wrap

        // TODO if this is last in the vertical layout then it is not visible
        this += new SLinearLayout {
          successButton = new SButton("Success")
          successButton.wrap.onClick {
            tiles = tiles match {
              case h :: t => t
              case Nil => Nil
            }
            newTile()
          }
          this += successButton

          failButton = new SButton("Fail")
          failButton.wrap.onClick {
            tiles = tiles match {
              case h :: t => t ++ List(h)
              case Nil => Nil
            }
            newTile() 
         }
          this += failButton

        }.wrap

        tileButton = new SImageButton()
        tileButton.scaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE).wrap.onClick {
          setTileSelected(true)
          showDevelopment = !showDevelopment
          displayTopTile()
        }
        this += tileButton

      }

    } padding 20.dip

    resetTiles()
  }

  var showDevelopment: Boolean = true

  def resetTiles() {
     tiles = rnd.shuffle((1 to NUM_TILES).toList.flatMap{ id => List(('d',id), ('w',id)) } )
     showDevelopment = true
     displayTopTile()
     setTileSelected(false)
  }

  // must be called after the top tile has changed
  def displayTopTile() {
    val resourceId = tiles match {
      case (tp, id) :: tail => (if (showDevelopment) R.drawable.d01 else R.drawable.w01) + id-1
      case Nil => R.drawable.cross
    }
    tileButton.setImageResource(resourceId)

    countTextView.setText(tiles.size + "/" + (NUM_TILES*2))
  }

  def newTile() {
    val tp = tiles match {
      case (tp, id) :: tail => tp
      case Nil => 'd'
    }
    showDevelopment = (tp == 'd')
    displayTopTile()
    setTileSelected(false)
  }

  var isTileSelected: Boolean = false
  def setTileSelected(b: Boolean) {
    isTileSelected = b
    successButton.enabled(isTileSelected)
    failButton.enabled(isTileSelected)
  }
}
