(ns app.core
  (:require
   [reagent.core :as reagent]
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce app-state
  (reagent/atom {}))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

;(defn page [ratom]
;  [:div
;   "Welcome to reagent-figwheel, ciao beatrice."])

(defn atom-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])

(defn page [ratom]
  (let [val1 (reagent/atom "NOR")
        val2 (reagent/atom "foot")]
    (fn []
      [:div
       [:h3 "ECG"]
       [:p "ECG value (NOR, VPBs, ISM, ISE): " @val1]
       [:p "ECG class from classification: " [atom-input val1]]
       [:h3 "EEG"]
       [:p "EEG value (left hand, right hand, foot, tongue)" @val2]
       [:p "EEG class from classification: " [atom-input val2]]])))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
