DESCRIPTION = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"


PV = "${KV}+${SRCDATE}"

SRC_URI = "https://github.com/open-amiko/driver-amiko/releases/download/${SRCDATE}/${MACHINE}-drivers-${KV}-${SRCDATE}.zip"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf ${nonarch_base_libdir}/modules/${KV}/extra"

do_compile() {
}

do_install() {
	install -d ${D}${nonarch_base_libdir}/modules/${KV}/extra
	install -d ${D}${sysconfdir}/modules-load.d
    install -m 0755 ${WORKDIR}/dvb.ko ${D}${nonarch_base_libdir}/modules/${KV}/extra
    echo dvb >> ${D}${sysconfdir}/modules-load.d/_${MACHINE}.conf
}
