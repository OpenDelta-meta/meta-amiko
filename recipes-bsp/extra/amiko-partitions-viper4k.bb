SUMMARY = "${MACHINE} partitions files"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit deploy update-rc.d

SRCDATE = "20200624"

PV = "${SRCDATE}"

COMPATIBLE_MACHINE = "^(viper4k)$"

S = "${WORKDIR}/patitions"

SRC_URI = "http://source.mynonpublic.com/amiko/${MACHINE}-partitions-${SRCDATE}.zip \
  file://flash-apploader \
"

INITSCRIPT_NAME = "flash-apploader"
INITSCRIPT_PARAMS = "start 90 S ."

SRC_URI[md5sum] = "c2bf35a2e3e268341a6a95169ec22637"
SRC_URI[sha256sum] = "7655317c26667edd443de5f0f77c6ca7d99dfae391c96ed861601d0ab5bb68b5"

ALLOW_EMPTY_${PN} = "1"
do_configure[nostamp] = "1"

do_install() {
    install -d ${D}${datadir}
    install -m 0644 ${S}/bootargs.bin ${D}${datadir}/bootargs.bin
    install -m 0644 ${S}/fastboot.bin ${D}${datadir}/fastboot.bin
    install -m 0644 ${S}/apploader.bin ${D}${datadir}/apploader.bin
    install -m 0755 -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/flash-apploader ${D}${sysconfdir}/init.d/flash-apploader
}

FILES_${PN} = "${datadir} ${sysconfdir}"

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/bootargs.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/boot.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/fastboot.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/apploader.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/pq_param.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/emmc_partitions.xml ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/baseparam.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/logo.img ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
    install -m 0755 ${S}/deviceinfo.bin ${DEPLOY_DIR_IMAGE}/${MACHINE}-partitions
}

addtask deploy before do_build after do_install

