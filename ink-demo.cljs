(ns ink-demo
  (:require ["ink" :refer [render Text Box]]
            [reagent.core :as r]))

(defonce state (r/atom 0))
(defonce state1 (r/atom 1))

(doseq [n (range 1 1000)]
  (js/setTimeout #(swap! state inc) (* n 500)))

(doseq [n (range 1 300)]
  (js/setTimeout #(swap! state1 inc) (* n 500)))

(defn hello []
  [:> Box {:flexDirection "column"}
   [:> Box {:flexGrow 1} [:> Text {:color "red"}
                          (str "Count in red: " @state)]]
   [:> Box {:flexGrow 1} [:> Text {:color "green"}
                          (str "Count in green: " @state1)]]])

(render (r/as-element [hello]))