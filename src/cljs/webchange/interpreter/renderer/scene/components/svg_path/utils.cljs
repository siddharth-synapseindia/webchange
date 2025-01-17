(ns webchange.interpreter.renderer.scene.components.svg-path.utils)

(defn set-svg-path
  [texture ctx {:keys [data dash fill width height]
                :or   {fill false}}]
  (.clearRect ctx 0 0 width height)

  (let [path (js/Path2D. data)]
    (when dash
      (.setLineDash ctx (clj->js dash)))
    (if fill
      (.fill ctx path)
      (.stroke ctx path)))

  (.update texture))

(defn re-draw
  [ctx texture {:keys [data stroke stroke-width line-cap dash fill scale]}]
  (doto ctx
    (set! -strokeStyle stroke)
    (set! -lineWidth stroke-width)
    (set! -lineCap line-cap)
    (.scale (:x scale) (:y scale)))

  (when (and fill (not (boolean? fill)))
    (set! (.-fillStyle ctx) fill))

  (set-svg-path texture ctx {:data data
                             :fill fill
                             :dash dash}))
