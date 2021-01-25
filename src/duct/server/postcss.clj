(ns duct.server.postcss
  (:require
   [clojure.string :as string]
   [duct.compile.postcss :refer [compile-postcss]]
   [hawk.core :as hawk]
   [integrant.core :as ig]))

(defn- output-file? [file opts]
  (-> file
      (.getAbsolutePath)
      (string/includes? (:output opts))))

(defn- css-file? [file]
  (re-matches #"[^.].*\.css$" (.getName file)))

(defn- postcss-file? [opts _ctx {:keys [file]}]
  (and (not (output-file? file opts))
       (css-file? file)))

(defn- postcss-handler [opts _ctx _event]
  (let [{:keys [out err]} (compile-postcss opts)]
    (when out (println out))
    (when err (println err))))

(defn start-watcher! [opts]
  (hawk/watch! [(merge {:paths ["resources/"]
                        :filter (partial postcss-file? opts)
                        :handler (partial postcss-handler opts)}
                       (select-keys opts [:paths :filter]))]))

(defmethod ig/init-key :duct.server/postcss [_ opts]
  (compile-postcss opts)
  (start-watcher! opts))

(defmethod ig/halt-key! :duct.server/postcss [_ opts]
  (hawk/stop! opts))

(defmethod ig/resume-key :duct.server/postcss [_key opts _old-opts _old-impl]
  (start-watcher! opts))

(defmethod ig/suspend-key! :duct.server/postcss [_ opts]
  (hawk/stop! opts))
