(ns duct.compile.postcss
  (:require
   [clojure.java.shell :as shell]
   [clojure.spec.alpha :as s]
   [integrant.core :as ig]))

(defn compile-postcss [{:keys [input output production postcss]}]
  (if production
    (apply shell/sh (flatten [(or postcss ["postcss"]) input "-o" output "--release"]))
    (apply shell/sh (flatten [(or postcss ["postcss"]) input "-o" output]))))

(defmethod ig/pre-init-spec :duct.compiler/postcss [_]
  (s/keys :req-un [::input ::output]
          :opt-un [::postcss ::paths ::filter]))

(defmethod ig/init-key :duct.compiler/postcss [_ opts]
  (compile-postcss opts))
