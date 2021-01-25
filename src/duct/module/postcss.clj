(ns duct.module.postcss
  (:require
   [clojure.spec.alpha :as s]
   [duct.core :as core]
   [integrant.core :as ig]))

(defn- env-duct-key [env]
  (case env
    :production :duct.compiler/postcss
    :duct.server/postcss))

(def ^:private development-config
  {})

(def ^:private production-config
  {:production true})

(def ^:private env-configs
  {:production production-config
   :development development-config})

(defmethod ig/pre-init-spec :duct.module/postcss [_]
  (s/keys :req-un [::input ::output]
          :opt-un [::postcss ::paths ::filter]))

(defmethod ig/init-key :duct.module/postcss
  [_ opts]
  (fn [config]
    (let [env  (::core/environment config :production)
          postcss-key (env-duct-key env)]
      (core/merge-configs config
                          {postcss-key opts}
                          {postcss-key (get env-configs env)}))))
