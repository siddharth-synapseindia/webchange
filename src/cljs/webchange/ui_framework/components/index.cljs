(ns webchange.ui-framework.components.index
  (:require
    [webchange.ui-framework.components.button.index :as button]
    [webchange.ui-framework.components.dialog.index :as dialog]
    [webchange.ui-framework.components.file.index :as file]
    [webchange.ui-framework.components.icon.index :as icon]
    [webchange.ui-framework.components.range-input.index :as range-input]
    [webchange.ui-framework.components.icon-button.index :as icon-button]
    [webchange.ui-framework.components.select.index :as select]
    [webchange.ui-framework.components.text-area.index :as text-area]
    [webchange.ui-framework.components.text-input.index :as text-input]
    [webchange.ui-framework.components.tooltip.index :as tooltip]))

(def button button/component)
(def dialog dialog/component)
(def file file/component)
(def icon icon/component)
(def icon-button icon-button/component)
(def range-input range-input/component)
(def select select/component)
(def text-area text-area/component)
(def text-input text-input/component)
(def tooltip tooltip/component)