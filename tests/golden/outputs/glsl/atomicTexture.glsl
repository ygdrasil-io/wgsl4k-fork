#version 450 core
precision highp float;
precision highp int;

layout(set = 0, binding = 0) uniform texture_storage_2d<r32uint, atomic> global_0;
layout(set = 0, binding = 1) uniform texture_storage_2d<r32sint, atomic> global_1;

void wgsl_cs_main(uvec3 id) {
    textureAtomicMax(global_0, ivec2(0, 0), 1u);
    textureAtomicMin(global_0, ivec2(0, 0), 1u);
    textureAtomicAdd(global_0, ivec2(0, 0), 1u);
    textureAtomicAnd(global_0, ivec2(0, 0), 1u);
    textureAtomicOr(global_0, ivec2(0, 0), 1u);
    textureAtomicXor(global_0, ivec2(0, 0), 1u);
    textureAtomicMax(global_1, ivec2(0, 0), 1);
    textureAtomicMin(global_1, ivec2(0, 0), 1);
    textureAtomicAdd(global_1, ivec2(0, 0), 1);
    textureAtomicAnd(global_1, ivec2(0, 0), 1);
    textureAtomicOr(global_1, ivec2(0, 0), 1);
    textureAtomicXor(global_1, ivec2(0, 0), 1);
}

texture_storage_2d<r32uint, atomic> textureAtomicMax(texture_storage_2d<r32uint, atomic> arg_0, ivec2 arg_1, uint arg_2) {
}

texture_storage_2d<r32uint, atomic> textureAtomicMin(texture_storage_2d<r32uint, atomic> arg_0, ivec2 arg_1, uint arg_2) {
}

texture_storage_2d<r32uint, atomic> textureAtomicAdd(texture_storage_2d<r32uint, atomic> arg_0, ivec2 arg_1, uint arg_2) {
}

texture_storage_2d<r32uint, atomic> textureAtomicAnd(texture_storage_2d<r32uint, atomic> arg_0, ivec2 arg_1, uint arg_2) {
}

texture_storage_2d<r32uint, atomic> textureAtomicOr(texture_storage_2d<r32uint, atomic> arg_0, ivec2 arg_1, uint arg_2) {
}

texture_storage_2d<r32uint, atomic> textureAtomicXor(texture_storage_2d<r32uint, atomic> arg_0, ivec2 arg_1, uint arg_2) {
}

texture_storage_2d<r32sint, atomic> textureAtomicMax(texture_storage_2d<r32sint, atomic> arg_0, ivec2 arg_1, int arg_2) {
}

texture_storage_2d<r32sint, atomic> textureAtomicMin(texture_storage_2d<r32sint, atomic> arg_0, ivec2 arg_1, int arg_2) {
}

texture_storage_2d<r32sint, atomic> textureAtomicAdd(texture_storage_2d<r32sint, atomic> arg_0, ivec2 arg_1, int arg_2) {
}

texture_storage_2d<r32sint, atomic> textureAtomicAnd(texture_storage_2d<r32sint, atomic> arg_0, ivec2 arg_1, int arg_2) {
}

texture_storage_2d<r32sint, atomic> textureAtomicOr(texture_storage_2d<r32sint, atomic> arg_0, ivec2 arg_1, int arg_2) {
}

texture_storage_2d<r32sint, atomic> textureAtomicXor(texture_storage_2d<r32sint, atomic> arg_0, ivec2 arg_1, int arg_2) {
}

layout(local_size_x = 2, local_size_y = 1, local_size_z = 1) in;
void main() {
    wgsl_cs_main(id);
}
