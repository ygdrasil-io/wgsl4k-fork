#version 450 core
#extension GL_EXT_shader_explicit_arithmetic_types_int64 : require
precision highp float;
precision highp int;

layout(set = 0, binding = 0) uniform texture_storage_2d<r64uint, atomic> global_0;

void wgsl_cs_main(uvec3 id) {
    textureAtomicMax(global_0, ivec2(0, 0), 1u);
    workgroupBarrier();
    textureAtomicMin(global_0, ivec2(0, 0), 1u);
}

texture_storage_2d<r64uint, atomic> textureAtomicMax(texture_storage_2d<r64uint, atomic> arg_0, ivec2 arg_1, uint64_t arg_2) {
}

void workgroupBarrier() {
}

texture_storage_2d<r64uint, atomic> textureAtomicMin(texture_storage_2d<r64uint, atomic> arg_0, ivec2 arg_1, uint64_t arg_2) {
}

layout(local_size_x = 2, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_cs_main(id);
}
