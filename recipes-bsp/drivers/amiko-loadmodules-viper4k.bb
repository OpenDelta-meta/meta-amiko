SUMMARY = "kernel modules load helper"
MAINTAINER = "AMIKO"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/license-gplv2.inc

COMPATIBLE_MACHINE = "^(viper4k)$"

SRC_URI = "file://amiko-loadmodules-viper4k.sh"

INITSCRIPT_NAME = "amiko-loadmodules"
INITSCRIPT_PARAMS = "start 01 S ."

inherit update-rc.d

S = "${WORKDIR}"

do_compile() {
}

do_install() {
    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/amiko-loadmodules-viper4k.sh ${D}${sysconfdir}/init.d/amiko-loadmodules
}
